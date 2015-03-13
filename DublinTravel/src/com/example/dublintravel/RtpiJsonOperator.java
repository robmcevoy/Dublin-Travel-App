package com.example.dublintravel;

import com.google.android.gms.maps.model.BitmapDescriptor;


public abstract class RtpiJsonOperator extends Operator{
	
	private static final long serialVersionUID = 7458768194716917137L;
	private final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private final String URL_MIDDLE = "&operator=";
	private final String URL_END = "&format=json";
	private final String URL_GET_STOPS_START = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=";
	private final String URL_GET_STOP_LOCATION = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?stopid=";
	private boolean NEEDS_AUTH=true;
	private Parser parser;
	
	public abstract BitmapDescriptor getMarkerColor(Controller controller);
	
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
	
	public boolean requireAdditionalLocationRequest() {
		return true;
	}
	
	public String generateStopLocationUrl(String stop) {
		return URL_GET_STOP_LOCATION + stop;
	}
	
	public boolean needsAuth(){
		return NEEDS_AUTH;
	}	
}
