package com.example.dublintravel;

public class Stop implements Comparable<Stop>{

	private String stopID;
	private String name;
	
	public Stop(String stopId, String name){
		this.stopID = stopId;
		this.name = name;
	}
	
	public String toString() { 
	    return name;
	} 
	
	public String getID(){
		return stopID;
	}
	
	public String getName(){
		return name;
	}
	
	public int compareTo(Stop other){
		return name.compareToIgnoreCase(other.getName());
	}
}
