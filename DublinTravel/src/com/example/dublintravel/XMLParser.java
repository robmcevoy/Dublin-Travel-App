package com.example.dublintravel;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

// used as a helper to parse XML format web service responses
public class XMLParser implements Parser {

	// returns a String representing the time until the next public transporter arrives at the stop provided
	public ArrayList<StopInfo> getStopInfo(String data){

		String duetime = ""; 
		String destination = "";
		String route="";
		String scheduledArrivalTime;
		String arrivalTime;
		SAXBuilder saxBuilder = new SAXBuilder();
		ArrayList<StopInfo> allStops = new ArrayList<StopInfo>();
		ArrayList<StopInfo> stopInfoArray = new ArrayList<StopInfo>();
		int count=0;
		StopInfo stopInfo;
		String errorMessage;
		
		try {
			Document doc = saxBuilder.build(new StringReader(data));
			Element rootNode = doc.getRootElement();
			Namespace namespace = rootNode.getNamespace();
		    List list = rootNode.getChildren("objStationData", namespace);
		    for(int i=0; (i<list.size()); i++){
		    	Element node = (Element)list.get(i);
		    	duetime = node.getChildText("Duein", namespace);
		    	destination = node.getChildText("Destination", namespace);
		    	route = node.getChildText("Direction", namespace);
		    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    	Date today = Calendar.getInstance().getTime();
		    	String dateString = df.format(today);
		    	arrivalTime = dateString + " " + node.getChildText("Exparrival", namespace) + ":00";
		    	scheduledArrivalTime = dateString + " " + node.getChildText("Scharrival", namespace) + ":00";
		    	stopInfo = new StopInfo(route, destination, duetime, arrivalTime, scheduledArrivalTime);
		    	allStops.add(stopInfo);
		    	count++;
		    }
 
		} catch (Exception e) {
			errorMessage = "caught exception " + e.toString();
			stopInfo = new StopInfo();
			stopInfo.setErrorMessage(errorMessage);
			stopInfoArray.add(stopInfo);
		}
		Collections.sort(allStops);
		for(int i=0; (i<count); i++){
			stopInfoArray.add(allStops.get(i));
		}
		return stopInfoArray;
	}
	
	public ArrayList<Stop> getStops(String data){
		ArrayList<Stop> stops = new ArrayList<Stop>();
		SAXBuilder saxBuilder = new SAXBuilder();
		String stopId;
		String name;
		Stop stop;
		try{
			Document doc = saxBuilder.build(new StringReader(data));
			Element rootNode = doc.getRootElement();
			Namespace namespace = rootNode.getNamespace();
		    List list = rootNode.getChildren("objStation", namespace);
		    for(int i=0; (i<list.size()); i++){
		    	Element node = (Element)list.get(i);
		    	stopId = node.getChildText("StationCode", namespace);
		    	name = node.getChildText("StationAlias", namespace);
		    	if(name.equals(""))
		    		name = node.getChildText("StationDesc", namespace);
		    	stop = new Stop(stopId, name);
		    	stops.add(stop);
		    	Collections.sort(stops);
		    }
		}
		catch (Exception e) {
		}
		return stops;
	}
}
