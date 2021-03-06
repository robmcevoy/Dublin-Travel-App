package com.example.dublintravel;

import com.google.android.gms.maps.GoogleMap;

import android.os.AsyncTask;

public class GetStopLocationThread extends AsyncTask<Operator, Void, String> {
	
	private HttpSender hs;
	private MapDashboardController controller;
	private Operator operator;
	
	public GetStopLocationThread(MapDashboardController controller){
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
		GoogleMap map = controller.getMap();
		map.addMarker(controller.getOperatorLocationMarker(operator));
		controller.decNumQueryingLocations();
	}

}
