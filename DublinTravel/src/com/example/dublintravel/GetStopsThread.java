package com.example.dublintravel;

import java.util.ArrayList;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class GetStopsThread extends AsyncTask<ListView, Integer, String> {
	
	private Operator operator;
	private ListView listview;
	private Context context;
	private HttpSender hs;
	private ProgressBar progressbar;
	private EditText searchBar;
	
	public GetStopsThread(Context context, Operator operator, ProgressBar progressbar, EditText searchBar){
		this.operator = operator;
		this.context = context;
		hs = new HttpSender();
		this.progressbar = progressbar;
		this.searchBar = searchBar;
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
		StopAdapter stopAdapter = new StopAdapter(context, android.R.layout.simple_list_item_1, stops  );
		listview.setAdapter(stopAdapter);
		progressbar.setVisibility(View.GONE);
		searchBar.setVisibility(View.VISIBLE);
	}
}
