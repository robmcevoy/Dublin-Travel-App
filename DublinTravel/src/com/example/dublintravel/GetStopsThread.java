package com.example.dublintravel;

import java.util.ArrayList;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class GetStopsThread extends AsyncTask<ListView, Integer, String> {
	
	private Operator operator;
	private ListView listview;
	private Context context;
	private HttpSender hs;
	private ArrayAdapter<Stop> adapter;
	private ProgressBar progressbar;
	
	GetStopsThread(Context context, Operator operator, ArrayAdapter<Stop> adapter, ProgressBar progressbar){
		this.operator = operator;
		this.context = context;
		hs = new HttpSender();
		this.adapter = adapter;
		this.progressbar = progressbar;
	}
	
	protected void onPreExecute(){
		super.onPreExecute();
		progressbar.setVisibility(View.VISIBLE);
	}
	
	protected void onProgressUpdate(Integer... values) {
		if (progressbar != null) {
	        progressbar.setProgress(values[0]);
	    }
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
		progressbar.setVisibility(View.GONE);
	}
}
