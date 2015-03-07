package com.example.dublintravel;

import java.io.Serializable;

public class Location implements Serializable{
	
	private static final long serialVersionUID = 8361758409104292980L;
	private double latitude;
	private double longitude;
	
	public Location(){}
	
	public Location(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location(String latitude, String longitude){
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public void setLatitude(String latitude){
		this.latitude = Double.parseDouble(latitude);
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude = Double.parseDouble(longitude);
	}
	
}
