package com.example.dublintravel;

public class StopInfo implements Comparable<StopInfo> {

	private String routeId;
	private String destination;
	private String dueTime;
	private String errorMessage;
	private boolean error; 
	private String arrivalTime;
	private String scheduledArrivalTime;
	
	StopInfo(String routeId, String destination, String dueTime, String arrivalTime, String scheduledArrivalTime){
		this.routeId = routeId;
		this.destination = destination;
		this.dueTime = dueTime;
		this.arrivalTime = arrivalTime;
		this.scheduledArrivalTime = scheduledArrivalTime;
	}
	
	StopInfo(){}
	
	public String getRouteId(){
		if(!error)
			return routeId;
		else 
			return "";
	}
	
	public String getDestination(){
		if(!error)
			return destination;
		else
			return errorMessage;
	}
	
	public String getDueTime(){
		if(!error)
			return dueTime;
		else
			return "";
	}
	
	public String getArrivalTime(){
		return arrivalTime;
	}
	
	void setErrorMessage(String message){
		this.errorMessage = message;
		this.error = true;
	}

	public int compareTo(StopInfo another) {
		String tempDueTime = dueTime;
		String anotherTempDueTime = another.getDueTime();
		
		// if duetime value is "due", give it a value of zero mins
		if(isDue(tempDueTime))	
			tempDueTime="0";
		if(isDue(anotherTempDueTime))
			anotherTempDueTime="0";
		
		if(Integer.parseInt(tempDueTime) < Integer.parseInt(anotherTempDueTime)){
			return -1;
		}
		else if(tempDueTime.equals(anotherTempDueTime)){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	public boolean isDue(String duetime){
		if(duetime.toLowerCase().equals("due")){
			return true;
		}
		return false;
	}
}
