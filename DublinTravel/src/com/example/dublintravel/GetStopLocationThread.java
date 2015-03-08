package com.example.dublintravel;

import android.os.AsyncTask;
import android.widget.Toast;

public class GetStopLocationThread extends AsyncTask<Operator, Void, String> {
	
	private HttpSender hs;
	private LiveMapController controller;
	private Operator operator;
	
	public GetStopLocationThread(LiveMapController controller){
		hs = new HttpSender();
		this.controller = controller;
	}

	@Override
	protected String doInBackground(Operator... arg0) {
		operator = arg0[0];
		Stop stop = operator.getPreviousStop();
		return hs.sendGetRequest(operator.generateStopLocationUrl(stop.getID()), operator.needsAuth());
	}
	
	protected void onPostExecute(String result) {
		Location location = operator.getParser().getStopLocation(result);
		controller.updateLocationOfOperator(location, operator);
		controller.decNumQueryingLocations();
		Toast.makeText(controller.getCurrentContext(), operator.getOperatorCode() + " set isQuerying to false",Toast.LENGTH_SHORT).show();
	}

}
