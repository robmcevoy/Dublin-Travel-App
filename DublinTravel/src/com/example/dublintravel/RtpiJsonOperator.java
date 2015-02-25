package com.example.dublintravel;

public class RtpiJsonOperator extends Operator{
	
	private static final long serialVersionUID = 7458768194716917137L;
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private final String URL_GET_STOPS_START = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=";
	private boolean NEEDS_AUTH=true;
	private Parser parser;
	
	protected RtpiJsonOperator(){
		parser = new JSONParser();
	}
	
	public Parser getParser(){
		return parser;
	}
	
	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + op_code + URL_END;
	}
	
	public String generateStopsUrl(){
		return URL_GET_STOPS_START + op_code + URL_END;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}

}
