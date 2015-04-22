package com.example.dublintravel;

import com.google.android.gms.maps.model.BitmapDescriptor;


public abstract class RtpiXmlOperator extends Operator{

	private static final long serialVersionUID = -5629661535826801033L;
	private final String URL_START = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";
	private final String STOPS_URL="http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML ";
	private static boolean NEEDS_AUTH=false;
	private static final Parser PARSER = new RtpiXmlParser();
	private static boolean REQUIRE_LOCATION_REQUEST=false;
	public abstract BitmapDescriptor getMarkerColor(Controller controller);
	
	protected RtpiXmlOperator(String op_code, int index){
		super(op_code, index, NEEDS_AUTH, PARSER, REQUIRE_LOCATION_REQUEST);
	}

	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop;
	}
	
	public String generateStopsUrl(){
		return STOPS_URL;
	}
	
	public String generateStopLocationUrl(String stop) {
		return "";
	}
}
