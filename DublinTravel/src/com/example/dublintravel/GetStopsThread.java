package com.example.dublintravel;

import java.util.ArrayList;
import android.os.AsyncTask;

public class GetStopsThread extends AsyncTask<Void, Integer, String> {
	
	private Operator operator;
	private HttpSender hs;
	private StopListDialog dialog;
	
	public GetStopsThread(StopListDialog dialog, Operator operator){
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
	
	protected String doInBackground(Void... arg0) {
		return hs.sendGetRequest(operator.generateStopsUrl(), operator.needsAuth());
	}

	protected void onPostExecute(String result) {
		ArrayList<Stop> stops = operator.getParser().getStops(result);
		dialog.setNotLoading();
		dialog.updateStops(stops);
		dialog.makeElementsVisible();
	}
}
