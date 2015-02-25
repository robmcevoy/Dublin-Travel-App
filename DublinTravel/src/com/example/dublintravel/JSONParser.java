package com.example.dublintravel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

//used as a helper to parse JSON format web service responses
public class JSONParser extends Parser {
	
	private static final long serialVersionUID = 4861814382930415890L;
	private final String NO_ERROR_CODE="0";
	private final String DEFAULT_ERROR_MESSAGE="Something has gone wrong";
	private final String ERROR_CODE="errorcode";
	private final String ERROR_MESSAGE="errormessage";
	private final String RESULTS="results";
	private final String DUETIME="duetime";
	private final String DESTINATION="destination";
	private final String ROUTE="route";
	private final String ARRIVAL_TIME="arrivaldatetime";
	private final String SCHEDULED_ARRIVAL_TIME="scheduledarrivaldatetime";
	private final String FULL_NAME="fullname";
	private final String SHORT_NAME="shortname";
	private final String STOP_ID="stopid";
	private final String NAME_JOIN=", ";
	
	public ArrayList<StopInfo> getStopInfo(String data){
		
		String duetime, destination, route, arrivalTime, scheduledArrivalTime, errorCode;
		String errorMessage=DEFAULT_ERROR_MESSAGE;
		ArrayList<StopInfo> stopInfoArray = new ArrayList<StopInfo>();
		int resultsLength;
		StopInfo stopInfo;
		try {
			JSONObject json = new JSONObject(data);
			errorCode = json.getString(ERROR_CODE);
			errorMessage = json.getString(ERROR_MESSAGE);
			if(errorCode.equals(NO_ERROR_CODE)){
				JSONArray result = json.getJSONArray(RESULTS);
				resultsLength = result.length();
				for(int i=0; i<resultsLength; i++){
					JSONObject resultItem = result.getJSONObject(i);
					duetime = convertToTimeFormat(resultItem.getString(DUETIME));
					destination = removeLUAS(resultItem.getString(DESTINATION));
					route = resultItem.getString(ROUTE);
					arrivalTime = resultItem.getString(ARRIVAL_TIME);
					scheduledArrivalTime = resultItem.getString(SCHEDULED_ARRIVAL_TIME);
					stopInfo = new StopInfo(route, destination, duetime, arrivalTime, scheduledArrivalTime);
					stopInfoArray.add(stopInfo);
				}
				Collections.sort(stopInfoArray);
				return stopInfoArray;
			}
			else{
				return createStopInfoError(errorMessage);
			}
		} catch (Exception e) {
			return createStopInfoError(errorMessage);
		}
	}
	
	public ArrayList<Stop> getStops(String data){
		ArrayList<Stop> stopArray = new ArrayList<Stop>();
		String errorCode, name, roadName, stopId;
		String errorMessage=DEFAULT_ERROR_MESSAGE;
		int resultsLength;
		Stop stop;
		try {
			JSONObject json = new JSONObject(data);
			errorCode = json.getString(ERROR_CODE);
			errorMessage = json.getString(ERROR_MESSAGE);
			if(errorCode.equals(NO_ERROR_CODE)){
				JSONArray result = json.getJSONArray(RESULTS);
				resultsLength = result.length();
				for(int i=0; i<resultsLength; i++){
					JSONObject resultItem = result.getJSONObject(i);
					name = removeLUAS(resultItem.getString(FULL_NAME));
					roadName = resultItem.getString(SHORT_NAME);
					if(!roadName.equals("")){
						name = name + NAME_JOIN + roadName;
					}
					stopId = resultItem.getString(STOP_ID);
					stop = new Stop(stopId, name);
					stopArray.add(stop);
				}
				Collections.sort(stopArray);
				return stopArray;
			}
			else{
				return createStopError(errorMessage);
			}
		} catch (Exception e) {
			return createStopError(errorMessage);
		}
	}
	
	private String convertToTimeFormat(String duetime){
		final String COLON = ":";
		final String DATE_FORMAT = "HH:mm";
		if(!duetime.contains(COLON)){
			return duetime;
		}
		else{
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			Date d1;
			Date now = new Date();
			long difference=0;
			String tmp;
			try {
				d1 = format.parse(duetime);
				tmp = format.format(now);
				now = format.parse(tmp);
				difference = d1.getTime() - now.getTime();
				difference = difference / (60 * 1000);
				
			} catch (ParseException e) {
				return duetime;
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