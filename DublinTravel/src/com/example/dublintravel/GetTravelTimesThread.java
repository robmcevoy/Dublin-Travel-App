package com.example.dublintravel;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetTravelTimesThread extends AsyncTask<TextView, Void, String>{
	
	private HttpSender hs;
	private TravelTimes travelTimes;
	private TextView textview;
	private android.location.Location currentLocation;
	private Stop stop;
	private GoogleDirectionsJsonParser parser;
	
	public GetTravelTimesThread(TravelTimes travelTimes, android.location.Location currentLocation, Stop stop){
		hs = new HttpSender();
		this.travelTimes = travelTimes;
		this.currentLocation = currentLocation;
		this.stop = stop;
		this.parser = new GoogleDirectionsJsonParser();
	}
	
	@Override
	protected String doInBackground(TextView... arg0) {
		textview = arg0[0];
		return hs.sendGetRequest(travelTimes.generateUrl(currentLocation, stop), false);
	}
	
	protected void onPostExecute(String result) {
		textview.setText(parser.getDistance(result));
	}
}
