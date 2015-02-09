package com.example.dublintravel;

public class Stop {

	private String stopID;
	private String name;
	
	Stop(String stopId, String name){
		this.stopID = stopID;
		this.name = name;
	}
	
	public String toString() { 
	    return name;
	} 
}
