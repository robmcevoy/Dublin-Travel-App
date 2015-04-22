package com.example.dublintravel;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleDirectionsJsonParser {
	
	private final String ROUTES = "routes";
	private final String LEGS = "legs";
	private final String DISTANCE = "distance";
	private final String DURATION = "duration";
	private final String TEXT = "text";
	private JSONObject leg;
	
	public void getLegs(String data){		
		try{
			JSONObject json = new JSONObject(data);
			JSONArray routes = json.getJSONArray(ROUTES);
			if(routes.length() > 0){
				JSONObject route = routes.getJSONObject(0);
				JSONArray legs = route.getJSONArray(LEGS);
				if(legs.length() > 0){
					leg = legs.getJSONObject(0);
				}
			}
		}
		catch(Exception e){}	
	}
	
	public String getDistance(){
		String result = "default";	
		try{	
			JSONObject distance = leg.getJSONObject(DISTANCE);
			result = distance.getString(TEXT);
		}
		catch(Exception e){
			result = e.getMessage();
		}	
		return result;		
	}
	
	public String getTime(){
		String result = "default";	
		try{	
			JSONObject duration = leg.getJSONObject(DURATION);
			result = duration.getString(TEXT);
		}
		catch(Exception e){
			result = e.getMessage();
		}	
		return result;		
	}
}
