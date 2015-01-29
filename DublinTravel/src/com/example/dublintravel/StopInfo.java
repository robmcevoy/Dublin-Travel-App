package com.example.dublintravel;

public class StopInfo {

	private String busId;
	private String destination;
	private String dueTime;
	
	StopInfo(String busId, String destination, String dueTime){
		this.busId = busId;
		this.destination = destination;
		this.dueTime = dueTime;
	}
	
	String getBusId(){
		return busId;
	}
	
	String getDestination(){
		return destination;
	}
	
	String getDueTime(){
		return dueTime;
	}
	
}
