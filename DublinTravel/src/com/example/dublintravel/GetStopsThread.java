package com.example.dublintravel;

import java.util.ArrayList;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetStopsThread extends AsyncTask<ListView, Void, String> {
	
	private Operator operator;
	private ListView listview;
	private Context context;
	private HttpSender hs;
	private ArrayAdapter<Stop> adapter;
	
	GetStopsThread(Context context, Operator operator, ArrayAdapter<Stop> adapter){
		this.operator = operator;
		this.context = context;
		hs = new HttpSender();
		this.adapter = adapter;
	}
	

	protected String doInBackground(ListView... arg0) {
		listview = arg0[0];
		return hs.sendGetRequest(operator.generateStopsUrl(), operator.needsAuth());
	}

	protected void onPostExecute(String result) {
		ArrayList<Stop> stops = operator.getParser().getStops(result);
		for(Stop s: stops){
			adapter.add(s);
		}
		
	}
}
