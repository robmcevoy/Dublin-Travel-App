package com.example.dublintravel;

public class Stop {

	private String stopID;
	private String name;
	
	Stop(String stopId, String name){
		this.stopID = stopId;
		this.name = name;
	}
	
	public String toString() { 
	    return name;
	} 
	
	public String getID(){
		return stopID;
	}
}
