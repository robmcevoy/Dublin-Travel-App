package com.example.dublintravel;

import java.io.Serializable;

public class Stop implements Comparable<Stop>, Serializable{

	private static final long serialVersionUID = -4720166227379166665L;
	private String stopID;
	private String name;
	private boolean error;
	private String errorMessage;
	
	public Stop(){}
	
	public Stop(String stopId, String name){
		this.stopID = stopId;
		this.name = name;
		this.error = false;
	}
	
	public void setErrorMessage(String message){
		this.errorMessage = message;
		this.error = true;
	} 
	
	public String getID(){
		if(!error)
			return stopID;
		else
			return "";
	}
	
	public String getName(){
		if(!error)
			return name;
		else
			return errorMessage;
	}
	
	public int compareTo(Stop other){
		return name.compareToIgnoreCase(other.getName());
	}
}
