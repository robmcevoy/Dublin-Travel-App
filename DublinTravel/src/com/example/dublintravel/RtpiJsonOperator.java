package com.example.dublintravel;

import com.google.android.gms.maps.model.BitmapDescriptor;


public abstract class RtpiJsonOperator extends Operator{
	
	private static final long serialVersionUID = 7458768194716917137L;
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private final String URL_GET_STOPS_START = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=";
	private final String URL_GET_STOP_LOCATION = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?stopid=";
	private static boolean NEEDS_AUTH=true;
	private static boolean REQUIRE_LOCATION_REQUEST=true;
	private static final Parser PARSER = new RtpiJsonParser();
	
	public abstract BitmapDescriptor getMarkerColor(Controller controller);
	
	protected RtpiJsonOperator(String op_code, int index){
		super(op_code, index, NEEDS_AUTH, PARSER, REQUIRE_LOCATION_REQUEST);
	}
	
	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + op_code + URL_END;
	}
	
	public String generateStopsUrl(){
		return URL_GET_STOPS_START + op_code + URL_END;
	}

	public String generateStopLocationUrl(String stop) {
		return URL_GET_STOP_LOCATION + stop;
	}	
}
