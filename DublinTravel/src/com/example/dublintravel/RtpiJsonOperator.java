package com.example.dublintravel;

import com.google.android.gms.maps.model.BitmapDescriptor;

/* Although this class extends the Operator class it is not a public transport operator itself
 * 3 public transport operators are available through the Rtpi Rest API
 * Dublin Bus, Luas & Bus Eireann
 * Each of these operators extend from this class
 * Data is returned from this API in Json format
 */

public abstract class RtpiJsonOperator extends Operator{
	
	private static final long serialVersionUID = 7458768194716917137L;
	private static final String URL_START = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=";
	private static final String URL_MIDDLE = "&operator=";
	private static final String URL_END = "&format=json";
	private static final String URL_GET_STOPS_START = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=";
	private static final String URL_GET_STOP_LOCATION = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?stopid=";
	private static final boolean NEEDS_AUTH=true;
	private static final boolean REQUIRE_LOCATION_REQUEST=true;
	private static final Parser PARSER = new RtpiJsonParser();
	
	public abstract BitmapDescriptor getMarkerColor(Controller controller);
	
	protected RtpiJsonOperator(String opCode, int index){
		super(opCode, index, NEEDS_AUTH, PARSER, REQUIRE_LOCATION_REQUEST);
	}
	
	public String generateRealtimeInfoUrlString(String stop) {
		return URL_START + stop + URL_MIDDLE + opCode + URL_END;
	}
	
	public String generateStopsUrl(){
		return URL_GET_STOPS_START + opCode + URL_END;
	}

	public String generateStopLocationUrl(String stop) {
		return URL_GET_STOP_LOCATION + stop;
	}	
}
