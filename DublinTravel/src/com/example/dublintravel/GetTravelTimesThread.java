package com.example.dublintravel;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetTravelTimesThread extends AsyncTask<TextView, Void, String>{
	
	private HttpSender hs;
	private TravelTimes travelTimes;
	private TextView distance;
	private TextView walk;
	private TextView cycle;
	private TextView driving;
	private android.location.Location currentLocation;
	private Stop stop;
	private GoogleDirectionsJsonParser parser;
	private ArrayList<String> data;
	
	public GetTravelTimesThread(android.location.Location currentLocation, Stop stop){
		hs = new HttpSender();
		this.travelTimes = new TravelTimes();
		this.currentLocation = currentLocation;
		this.stop = stop;
		this.parser = new GoogleDirectionsJsonParser();
		this.data = new ArrayList<String>();
	}
	
	@Override
	protected String doInBackground(TextView... arg) {
		if(arg.length == (travelTimes.getNumTravelModes() + 1)){
			distance = arg[0];
			walk = arg[1];
			cycle = arg[2];
			driving = arg[3];
			data.add(hs.sendGetRequest(travelTimes.generateUrl(currentLocation, stop), false));
			travelTimes.incIndex();
			data.add(hs.sendGetRequest(travelTimes.generateUrl(currentLocation, stop), false));
			travelTimes.incIndex();
			data.add(hs.sendGetRequest(travelTimes.generateUrl(currentLocation, stop), false));
		}
		return null;
	}
	
	protected void onPostExecute(String result) {
		parser.getLegs(data.get(0));
		distance.setText(parser.getDistance());
		walk.setText(parser.getTime());
		parser.getLegs(data.get(1));
		cycle.setText(parser.getTime());
		parser.getLegs(data.get(2));
		driving.setText(parser.getTime());
	}
}
