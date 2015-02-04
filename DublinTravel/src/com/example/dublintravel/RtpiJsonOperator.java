package com.example.dublintravel;

public class RtpiJsonOperator implements Operator {
	
	protected String OPERATOR_CODE="";
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private boolean NEEDS_AUTH=true;
	private Parser parser;
	
	RtpiJsonOperator(){
		parser = new JSONParser();
	}
	
	public Parser getParser(){
		return parser;
	}
	
	public String generateUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + OPERATOR_CODE + URL_END;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}

}
