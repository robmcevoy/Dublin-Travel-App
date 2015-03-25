package com.example.dublintravel;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleDirectionsJsonParser {
	
	private final String ROUTES = "routes";
	private final String LEGS = "legs";
	private final String DISTANCE = "distance";
	private final String TEXT = "text";
	
	public String getDistance(String data){
		String result = "default";		
		try{
			JSONObject json = new JSONObject(data);
			JSONArray routes = json.getJSONArray(ROUTES);
			if(routes.length() > 0){
				JSONObject route = routes.getJSONObject(0);
				JSONArray legs = route.getJSONArray(LEGS);
				if(legs.length() > 0){
					JSONObject leg = legs.getJSONObject(0);
					JSONObject distance = leg.getJSONObject(DISTANCE);
					result = distance.getString(TEXT);
				}
			}
		}
		catch(Exception e){
			result = e.getMessage();
		}	
		return result;		
	}
}
