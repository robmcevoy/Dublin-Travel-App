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
public class XMLParser extends Parser {
	
	private final String DEFAULT_ERROR_MESSAGE="Something has gone wrong";
	private final String OBJ_STATION_DATA = "objStationData";
	private final String OBJ_STATION = "objStation";
	private final String DUE_IN = "Duein";
	private final String DESTINATION="Destination";
	private final String DIRECTION = "Direction";
	private final String DATE_FORMAT= "dd/MM/yyyy";
	private final String EXP_ARRIVAL= "Exparrival";
	private final String SCH_ARRIVAL= "Scharrival";
	private final String SECONDS_CONCAT=":00";
	private final String STATION_CODE="StationCode";
	private final String STATION_ALIAS="StationAlias";
	private final String STATION_DESC="StationDesc";

	// returns a String representing the time until the next public transporter arrives at the stop provided
	public ArrayList<StopInfo> getStopInfo(String data){

		String duetime, destination, route, scheduledArrivalTime, arrivalTime;
		String errorMessage=DEFAULT_ERROR_MESSAGE;
		SAXBuilder saxBuilder = new SAXBuilder();
		ArrayList<StopInfo> allStops = new ArrayList<StopInfo>();
		ArrayList<StopInfo> stopInfoArray = new ArrayList<StopInfo>();
		int count=0;
		StopInfo stopInfo;
		
		try {
			Document doc = saxBuilder.build(new StringReader(data));
			Element rootNode = doc.getRootElement();
			Namespace namespace = rootNode.getNamespace();
		    List list = rootNode.getChildren(OBJ_STATION_DATA, namespace);
		    for(int i=0; (i<list.size()); i++){
		    	Element node = (Element)list.get(i);
		    	duetime = node.getChildText(DUE_IN, namespace);
		    	destination = node.getChildText(DESTINATION, namespace);
		    	route = node.getChildText(DIRECTION, namespace);
		    	DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		    	Date today = Calendar.getInstance().getTime();
		    	String dateString = df.format(today);
		    	arrivalTime = dateString + " " + node.getChildText(EXP_ARRIVAL, namespace) + SECONDS_CONCAT;
		    	scheduledArrivalTime = dateString + " " + node.getChildText(SCH_ARRIVAL, namespace) + SECONDS_CONCAT;
		    	stopInfo = new StopInfo(route, destination, duetime, arrivalTime, scheduledArrivalTime);
		    	allStops.add(stopInfo);
		    	count++;
		    }
 
		} catch (Exception e) {
			return createStopInfoError(errorMessage);
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
		Stop stop;
		String stopId, name;
		String errorMessage=DEFAULT_ERROR_MESSAGE;
		try{
			Document doc = saxBuilder.build(new StringReader(data));
			Element rootNode = doc.getRootElement();
			Namespace namespace = rootNode.getNamespace();
		    List list = rootNode.getChildren(OBJ_STATION, namespace);
		    for(int i=0; (i<list.size()); i++){
		    	Element node = (Element)list.get(i);
		    	stopId = node.getChildText(STATION_CODE, namespace);
		    	name = node.getChildText(STATION_ALIAS, namespace);
		    	if(name.equals(""))
		    		name = node.getChildText(STATION_DESC, namespace);
		    	stop = new Stop(stopId, name);
		    	stops.add(stop);
		    	Collections.sort(stops);
		    }
		}
		catch (Exception e) {
			return createStopError(errorMessage);
		}
		return stops;
	}
}
