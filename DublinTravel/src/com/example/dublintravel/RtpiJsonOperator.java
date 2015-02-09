package com.example.dublintravel;

public class RtpiJsonOperator extends Operator{
	
	protected String OPERATOR_CODE="";
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private final String URL_GET_STOPS_START = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=";
	private boolean NEEDS_AUTH=true;
	private Parser parser;
	
	RtpiJsonOperator(){
		parser = new JSONParser();
	}
	
	public Parser getParser(){
		return parser;
	}
	
	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + OPERATOR_CODE + URL_END;
	}
	
	public String generateStopsUrl(){
		return URL_GET_STOPS_START + OPERATOR_CODE + URL_END;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}
	
	public String getOperatorCode(){
		return OPERATOR_CODE;
	}

}
