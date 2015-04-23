package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* Represents the Irish Rail public transport operator 
 * Data is returned from this API in XMl format*/

public class IrishRailOperator extends Operator{

	private static final long serialVersionUID = -5629661535826801033L;
	private final String URL_START = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";
	private final String STOPS_URL="http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML ";
	private static boolean NEEDS_AUTH=false;
	private static final Parser PARSER = new IrishRailXmlParser();
	private static boolean REQUIRE_LOCATION_REQUEST=false;
	private final static String OP_CODE="ir";
	private final static int INDEX=3;
	
	public IrishRailOperator(){
		super(OP_CODE, INDEX, NEEDS_AUTH, PARSER, REQUIRE_LOCATION_REQUEST);
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
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
	}
}
