package com.example.dublintravel;

public class StopInfo {

	private String routeId;
	private String destination;
	private String dueTime;
	private String errorMessage;
	private boolean error; 
	
	StopInfo(String routeId, String destination, String dueTime){
		this.routeId = routeId;
		this.destination = destination;
		this.dueTime = dueTime;
	}
	
	StopInfo(){}
	
	String getRouteId(){
		if(!error)
			return routeId;
		else 
			return null;
	}
	
	String getDestination(){
		if(!error)
			return destination;
		else
			return errorMessage;
	}
	
	String getDueTime(){
		if(!error)
			return dueTime;
		else
			return null;
	}
	
	void setErrorMessage(String message){
		this.errorMessage = message;
		this.error = true;
	}
	
}
