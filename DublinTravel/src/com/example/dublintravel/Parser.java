package com.example.dublintravel;

import java.util.ArrayList;

public abstract class Parser {

	public abstract ArrayList<StopInfo> getStopInfo(String data);
	
	public abstract ArrayList<Stop> getStops(String data);
	
	protected ArrayList<Stop> createStopError(String errorMessage){
		ArrayList<Stop> errorArray = new ArrayList<Stop>();
		Stop stop = new Stop();
		stop.setErrorMessage(errorMessage);
		errorArray.add(stop);
		return errorArray;
	}
	
	protected ArrayList<StopInfo> createStopInfoError(String errorMessage){
		ArrayList<StopInfo> errorArray = new ArrayList<StopInfo>();
		StopInfo stopInfo = new StopInfo();
		stopInfo.setErrorMessage(errorMessage);
		errorArray.add(stopInfo);
		return errorArray;
	}
}
