package com.example.dublintravel;


public class RtpiXmlOperator extends Operator{

	private static final long serialVersionUID = -5629661535826801033L;
	private final String URL_START = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";
	private final String STOPS_URL="http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML ";
	private Parser parser;
	private boolean NEEDS_AUTH=false;
	
	protected RtpiXmlOperator(){
		parser = new XMLParser();
	}
	
	public Parser getParser() {
		return parser;
	}

	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop;
	}
	
	public String generateStopsUrl(){
		return STOPS_URL;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}
}
