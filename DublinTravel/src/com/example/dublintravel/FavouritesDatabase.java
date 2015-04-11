package com.example.dublintravel;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class FavouritesDatabase {
	
	private static final String DATABASE_TABLE = "favoutites";
	private static final String KEY_ID = "id";
	private static final String KEY_STOP_ID = "stop_id";
	private static final String KEY_OPERATOR = "operator";
	private static final String KEY_NAME = "name";
	
	private static final String DATABASE_NAME = "my_stop_database";
	private static final int DATABASE_VERSION = 15;
	
	private DbHelper helper;
	private final Context context;
	private SQLiteDatabase database;
	
	public FavouritesDatabase(Context context){
		this.context = context;
	}
	
	public FavouritesDatabase open() throws SQLException{
		helper = new DbHelper(context);
		database = helper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		helper.close();
	}
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}

		public void onCreate(SQLiteDatabase db) throws SQLiteException {
				db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
							KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_STOP_ID + " TEXT NOT NULL, " +
							KEY_NAME + " TEXT NOT NULL, " +
							KEY_OPERATOR + " TEXT NOT NULL); "	
							);
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public void addFavourite(Stop stop, Operator operator){
		createEntry(stop.getName(), stop.getID(), operator.getOperatorCode());
	}
	
	public void deleteFavorite(Stop stop, Operator operator){
		String columns[] = new String[]{ KEY_ID, KEY_STOP_ID, KEY_NAME, KEY_OPERATOR};
		Cursor cursor = database.query(DATABASE_TABLE, columns, null, null, null, null, null);
		int iStopId = cursor.getColumnIndex(KEY_STOP_ID);
		int iName = cursor.getColumnIndex(KEY_NAME);
		int iOperator = cursor.getColumnIndex(KEY_OPERATOR);
		int id = cursor.getColumnIndex(KEY_ID);
		int row = -1;
		boolean foundRow = false;
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			if(operator.getOperatorCode().equals(cursor.getString(iOperator))){
				Stop tmp = new Stop(cursor.getString(iStopId),cursor.getString(iName));
				if(stop.equals(tmp)){
					row = cursor.getInt(id);
					foundRow = true;
				}
			}
		}
		if(foundRow){
			database.delete(DATABASE_TABLE, KEY_ID + "=" + row, null);
		}
	}
	
	public ArrayList<Stop> getFavourites(Operator operator){

		ArrayList<Stop> stops = new ArrayList<Stop>();
		String columns[] = new String[]{ KEY_ID, KEY_STOP_ID, KEY_NAME, KEY_OPERATOR};
		Cursor cursor = database.query(DATABASE_TABLE, columns, null, null, null, null, null);
		int iStopId = cursor.getColumnIndex(KEY_STOP_ID);
		int iName = cursor.getColumnIndex(KEY_NAME);
		int iOperator = cursor.getColumnIndex(KEY_OPERATOR);
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			if(operator.getOperatorCode().equals(cursor.getString(iOperator))){
				Stop stop = new Stop(cursor.getString(iStopId),cursor.getString(iName));
				stops.add(stop);	
			}
		}
		cursor.close();
		return stops;
	}
	
	private long createEntry(String name, String stopId, String operator) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_STOP_ID, stopId);
		cv.put(KEY_OPERATOR, operator);
		return database.insert(DATABASE_TABLE, null, cv);
	}
}
