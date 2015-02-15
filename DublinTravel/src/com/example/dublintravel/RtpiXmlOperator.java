package com.example.dublintravel;

public class RtpiXmlOperator extends Operator{

	protected String OPERATOR_CODE="";
	private final String URL_START = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";
	private final String STOPS_URL="http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML ";
	private Parser parser;
	private boolean NEEDS_AUTH=false;
	
	public RtpiXmlOperator(){
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
	
	public String getOperatorCode(){
		return OPERATOR_CODE;
	}

}
