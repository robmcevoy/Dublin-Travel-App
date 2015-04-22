package com.example.dublintravel;

public class TravelTimes {
	
	private int currentIndex;
	private final String[] strings = {"walking","bicycling","driving"};
	private final String URL_START = "https://maps.googleapis.com/maps/api/directions/json?";
	private final String KEY = "&key=AIzaSyAhBbT7FiWcctAjQBnbwvIy6MiLZ7A6JTE";
	private final String ORIGIN = "origin=";
	private final String DESTINATION = "&destination=";
	private final String MODE = "&mode=";
	//https://maps.googleapis.com/maps/api/directions/json?origin=53.3808588,-6.3727567&destination=53.378787,-6.372521&mode=bicycling&key=AIzaSyAhBbT7FiWcctAjQBnbwvIy6MiLZ7A6JTE
	
	TravelTimes(){
		currentIndex=0;
	}
	
	public void resetIndex(){
		currentIndex=0;
	}
	
	public void incIndex(){
		if(currentIndex < (strings.length-1))
			currentIndex++;
	}
	
	public int getNumTravelModes(){
		return strings.length;
	}
	
	public String generateUrl(android.location.Location currentLocation, Stop stop){
		return URL_START+ ORIGIN + currentLocation.getLatitude() + "," + currentLocation.getLongitude()
				+ DESTINATION + stop.getLocation().getLatitude() + "," + stop.getLocation().getLongitude()
				+ MODE + strings[currentIndex] + KEY;
	}

}
