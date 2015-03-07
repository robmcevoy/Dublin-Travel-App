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
	
	private static final long serialVersionUID = 2011543856038658256L;
	private final String OBJ_STATION_DATA = "objStationData";
	private final String OBJ_STATION = "objStation";
	private final String DUE_IN = "Duein";
	private final String DESTINATION="Destination";
	private final String DIRECTION = "Direction";
	private final String DATE_FORMAT= "dd/MM/yyyy";
	private final String EXP_ARRIVAL= "Exparrival";
	private final String SCH_ARRIVAL= "Scharrival";
	private final String EXP_DEPART= "Expdepart";
	private final String SCH_DEPART= "Schdepart";
	private final String STATION_LATITUDE = "StationLatitude";
	private final String STATION_LONGITUDE = "StationLongitude";
	private final String SECONDS_CONCAT=":00";
	private final String STATION_CODE="StationCode";
	private final String STATION_ALIAS="StationAlias";
	private final String STATION_DESC="StationDesc";
	private final String INVALID = "00:00";
	private final String SERVER_TIME="Servertime";

	// returns a String representing the time until the next public transporter arrives at the stop provided
	public ArrayList<StopInfo> getStopInfo(String data){

		String duetime, destination, route, scheduledArrivalTime, arrivalTime, serverTime;
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
		    if(list.size() > 0){
		    	for(int i=0; (i<list.size()); i++){
		    		Element node = (Element)list.get(i);
		    		duetime = node.getChildText(DUE_IN, namespace);
		    		destination = node.getChildText(DESTINATION, namespace);
		    		route = node.getChildText(DIRECTION, namespace);
		    		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		    		Date today = Calendar.getInstance().getTime();
		    		String dateString = df.format(today);
		    		arrivalTime = dateString + " " + node.getChildText(EXP_ARRIVAL, namespace);
		    		scheduledArrivalTime = dateString + " " + node.getChildText(SCH_ARRIVAL, namespace);
		    		/* if the exp and sch arrival times are both 00:00 then the train has not left yet
		    		 * take the exp and sch departure times instead
		    		 */
		    		if(arrivalTime.equals(dateString + " " +INVALID) && scheduledArrivalTime.equals(dateString + " " +INVALID)){
		    			arrivalTime = dateString + " " + node.getChildText(EXP_DEPART, namespace);
		    			scheduledArrivalTime = dateString + " " + node.getChildText(SCH_DEPART, namespace);
		    		}
		    		arrivalTime = arrivalTime + SECONDS_CONCAT;
		    		scheduledArrivalTime = scheduledArrivalTime + SECONDS_CONCAT;
		    		serverTime = node.getChildText(SERVER_TIME, namespace);
		    		stopInfo = new StopInfo(route, destination, duetime, arrivalTime, scheduledArrivalTime, serverTime);
		    		allStops.add(stopInfo);
		    		count++;
		    	}
		    	Collections.sort(allStops);
				for(int i=0; (i<count); i++){
					stopInfoArray.add(allStops.get(i));
				}
		    }
		    else{
		    	stopInfoArray = createStopInfoError(errorMessage);
		    }
		} catch (Exception e) {
			stopInfoArray = createStopInfoError(errorMessage);
		}
		return stopInfoArray;
	}

	public ArrayList<Stop> getStops(String data){
		ArrayList<Stop> stops = new ArrayList<Stop>();
		SAXBuilder saxBuilder = new SAXBuilder();
		Stop stop;
		String stopId, name, longitude, latitude;
		Location location;
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
		    	longitude = node.getChildText(STATION_LONGITUDE, namespace);
		    	latitude = node.getChildText(STATION_LATITUDE, namespace);
		    	location = new Location(latitude, longitude);
		    	if(name.equals(""))
		    		name = node.getChildText(STATION_DESC, namespace);
		    	stop = new Stop(stopId, name, location);
		    	stops.add(stop);
		    	Collections.sort(stops);
		    }
		}
		catch (Exception e) {
			return createStopError(errorMessage);
		}
		return stops;
	}

	public Location getStopLocation(String data) {
		return null;
	}
}
