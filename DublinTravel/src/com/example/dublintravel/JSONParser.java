package com.example.dublintravel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//used as a helper to parse JSON format web service responses
public class JSONParser implements Parser {
	
	final String NO_ERROR_CODE="0";
	
	public ArrayList<StopInfo> getStopInfo(String data){
		
		String duetime="";
		String destination="";
		String route="";
		String errorCode="";
		String errorMessage="";
		ArrayList<StopInfo> stopInfoArray = new ArrayList<StopInfo>();
		final int MAX_NUM_RESULTS=5;
		final String NO_ERROR_CODE="0";
		int resultsLength;
		StopInfo stopInfo;
		try {
			JSONObject json = new JSONObject(data);
			errorCode = json.getString("errorcode");
			errorMessage = json.getString("errormessage");
			if(errorCode.equals(NO_ERROR_CODE)){
				JSONArray result = json.getJSONArray("results");
				resultsLength = result.length();
				for(int i=0; ((i<resultsLength) && (i<MAX_NUM_RESULTS)); i++){
					JSONObject resultItem = result.getJSONObject(i);
					duetime = convertToTimeFormat(resultItem.getString("duetime"));
					destination = removeLUAS(resultItem.getString("destination"));
					route = resultItem.getString("route");
					stopInfo = new StopInfo(route, destination, duetime);
					stopInfoArray.add(stopInfo);
				}
			}
			else{
				stopInfo = new StopInfo();
				stopInfo.setErrorMessage(errorMessage);
				stopInfoArray.add(stopInfo);
			}
			return stopInfoArray;
			
		} catch (JSONException e) {
			errorMessage = "caught exception " + e.toString();
			stopInfo = new StopInfo();
			stopInfo.setErrorMessage(errorMessage);
			stopInfoArray.add(stopInfo);
			return stopInfoArray;
		}
	}
	
	public ArrayList<Stop> getStops(String data){
		ArrayList<Stop> stopArray = new ArrayList<Stop>();
		String errorCode;
		String errorMessage;
		int resultsLength;
		String name;
		String stopId;
		Stop stop;
		try {
			JSONObject json = new JSONObject(data);
			errorCode = json.getString("errorcode");
			errorMessage = json.getString("errormessage");
			if(errorCode.equals(NO_ERROR_CODE)){
				JSONArray result = json.getJSONArray("results");
				resultsLength = result.length();
				for(int i=0; i<resultsLength; i++){
					JSONObject resultItem = result.getJSONObject(i);
					name = removeLUAS(resultItem.getString("fullname"));
					stopId = resultItem.getString("stopid");
					stop = new Stop(stopId, name);
					stopArray.add(stop);
				}
			}
			else{
			}
		} catch (JSONException e) {

		}
		return stopArray;
	}
	
	private String convertToTimeFormat(String duetime){
		
		if(!duetime.contains(":")){
			return duetime;
		}
		else{
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date d1;
			Date now = new Date();
			long difference=0;
			String tmp;
			try {
				d1 = format.parse(duetime);
				tmp = format.format(now);
				now = format.parse(tmp);
				difference = d1.getTime() - now.getTime();
				difference = difference / (60 * 1000) % 60;
				
			} catch (ParseException e) {
			}
			return Long.toString(difference);
		}
	}
	
	private String removeLUAS(String s){
		final String LUAS = "LUAS";
		int index;
		if(s.contains(LUAS)){
			index = s.indexOf(LUAS);
			return s.substring(index+LUAS.length());
		}
		return s;
	}

}
