package com.example.dublintravel;

import java.io.Serializable;

/* encapsulates a Bus, Tram Stop or Train Station */

public class Stop implements Comparable<Stop>, Serializable{

	private static final long serialVersionUID = -4720166227379166665L;
	private String stopID;
	private String name;
	private boolean error;
	private String errorMessage;
	private Location location;
	private boolean favourite;
	
	public Stop(){}
	
	public Stop(String stopId, String name){
		this.stopID = stopId;
		this.name = name;
		this.error = false;
		this.favourite = false;
	}
	
	public Stop(String stopId, String name, Location location){
		this.stopID = stopId;
		this.name = name;
		this.location = location;
		this.error = false;
		this.favourite = false;
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
	
	public boolean equals(Stop other){
		return (name.compareToIgnoreCase(other.getName()) == 0) && (stopID.equals(other.getID()));
	}
	
	public void setLocation(Location location){
		this.location = location;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public boolean hasLocation(){
		return location != null;
	}
	
	public void favourite(){
		this.favourite = true;
	}
	
	public void unfavourite(){
		this.favourite = false;
	}
	
	public boolean isFavourite(){
		return this.favourite;
	}
}
