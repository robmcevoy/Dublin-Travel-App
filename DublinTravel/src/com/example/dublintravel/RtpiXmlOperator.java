package com.example.dublintravel;

public class RtpiXmlOperator extends Operator{

	protected String OPERATOR_CODE="";
	private final String URL_START = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";
	private Parser parser;
	private boolean NEEDS_AUTH=false;
	
	RtpiXmlOperator(){
		parser = new XMLParser();
	}
	
	public Parser getParser() {
		return parser;
	}

	public String generateUrlString(String stop) {
		return URL_START + stop;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}
	
	public String getOperatorCode(){
		return OPERATOR_CODE;
	}

}
