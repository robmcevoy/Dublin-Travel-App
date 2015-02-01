package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GetThread extends AsyncTask<StopInfoTable, Void, String>{

	StopInfoTable stopInfoTable;
	Context context;
	HttpSender hs;
	JSONHelper jh;
	String stop;
	
	public GetThread(Context context, String stop){
		this.context = context;
		hs = new HttpSender();
		jh = new JSONHelper();
		this.stop =stop;
	}
	
	protected String doInBackground(StopInfoTable... arg0) {
		stopInfoTable = arg0[0]; 
		return hs.sendGetRequest("http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid="+stop+"&operator=bac&format=json", true);
	}
	
	protected void onPostExecute(String result) {

		wipe();
		ArrayList<StopInfo> stopInfoArray = jh.getJSONNextDue(result);
		TextView route;
		TextView dest;
		TextView duetime;
		int index = 0;
		for(StopInfo stopInfo:stopInfoArray){
			route = stopInfoTable.getTableElement(index);
			dest = stopInfoTable.getTableElement(index+1);
			duetime = stopInfoTable.getTableElement(index+2);
			route.setText(stopInfo.getRouteId());
			dest.setText(stopInfo.getDestination());
			duetime.setText(stopInfo.getDueTime());
			index = index+3;
		}
    }
	
	public void wipe(){
		TextView textView;
		for(int i=0; i<stopInfoTable.getTableSize(); i++){
			textView = stopInfoTable.getTableElement(i);
			textView.setText("");
		}
	}

}
