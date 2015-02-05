package com.example.dublintravel;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
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
		SAXBuilder saxBuilder = new SAXBuilder();
		final int MAX_NUM_RESULTS=5;
		
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
		    	stopInfo = new StopInfo(route, destination, duetime);
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
		for(int i=0; ((i< MAX_NUM_RESULTS) && i<count); i++){
			stopInfoArray.add(allStops.get(i));
		}
		return stopInfoArray;
	}
}
