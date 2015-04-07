package com.example.dublintravel;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetStopsThread extends AsyncTask<ListView, Integer, String> {
	
	private Operator operator;
	private ListView listview;
	private HttpSender hs;
	private RtpiController rtpiController;
	private StopListDialog dialog;
	
	public GetStopsThread(RtpiController rtpiController, StopListDialog dialog, Operator operator){
		this.rtpiController = rtpiController;
		this.operator = operator;
		hs = new HttpSender();
		this.dialog = dialog;
	}
	
	protected void onPreExecute(){
		dialog.setLoading();
		super.onPreExecute();
	}
	
	protected void onProgressUpdate(Integer... values) {
		dialog.setLoadingProgress(values);
	}
	
	protected String doInBackground(ListView... arg0) {
		listview = arg0[0];
		return hs.sendGetRequest(operator.generateStopsUrl(), operator.needsAuth());
	}

	protected void onPostExecute(String result) {
		ArrayList<Stop> stops = operator.getParser().getStops(result);
		StopAdapter stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1, stops);
		listview.setAdapter(stopAdapter);
		dialog.setNotLoading();
		dialog.makeElementsVisible();
	}
}
