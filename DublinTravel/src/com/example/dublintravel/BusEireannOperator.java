package com.example.dublintravel;

public class BusEireannOperator implements Operator {

	private final String OPERATOR_CODE="be";
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private Parser parser;
	
	BusEireannOperator(){
		parser = new JSONParser();
	}
	
	public Parser getParser(){
		return parser;
	}
	
	public String generateUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + OPERATOR_CODE + URL_END;
	}
}
