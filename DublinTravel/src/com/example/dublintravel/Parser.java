package com.example.dublintravel;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Parser implements Serializable {

	private static final long serialVersionUID = -7454288656434101180L;

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
