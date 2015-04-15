import java.util.ArrayList;

import junit.framework.TestCase;

import com.example.dublintravel.Location;
import com.example.dublintravel.RtpiJsonParser;
import com.example.dublintravel.StopInfo;
import com.example.dublintravel.Stop;


public class RtpiJsonParserTests extends TestCase {
	
	private RtpiJsonParser parser;
	private final String EXPECTED_DUE_TIME= "17";
	private final String EXPECTED_DESTINATON= "Wilton Terrace";
	private final String EXPECTED_ROUTE= "37";
	private final String EXPECTED_ARRIVAL_TIME= "15/04/2015 16:42:24";
	private final String EXPECTED_SCH_ARRIVAL_TIME= "15/04/2015 16:42:00";
	private final String EXPECTED_SERVER_TIME= "15/04/2015 16:24:27";
	private final String EXPECTED_STOP_ID= "LUAS1";
	private final String EXPECTED_STOP_NAME= " Tallaght";
	private final double EXPECTED_LATITUDE= 53.378812;
	private final double EXPECTED_LONGITUDE= -6.372531;
	private final String TEST_JSON_1 =
	"{\n" + 
	"    \"errorcode\": \"0\",\n" + 
	"    \"errormessage\": \"\",\n" + 
	"    \"numberofresults\": 3,\n" + 
	"    \"stopid\": \"1686\",\n" + 
	"    \"timestamp\": \"15/04/2015 16:24:27\",\n" + 
	"    \"results\": [\n" + 
	"        {\n" + 
	"            \"arrivaldatetime\": \"15/04/2015 16:42:24\",\n" + 
	"            \"duetime\": \"17\",\n" + 
	"            \"departuredatetime\": \"15/04/2015 16:42:24\",\n" + 
	"            \"departureduetime\": \"17\",\n" + 
	"            \"scheduledarrivaldatetime\": \"15/04/2015 16:42:00\",\n" + 
	"            \"scheduleddeparturedatetime\": \"15/04/2015 16:42:00\",\n" + 
	"            \"destination\": \"Wilton Terrace\",\n" + 
	"            \"destinationlocalized\": \"Wilton Terrace\",\n" + 
	"            \"origin\": \"Blanchardstown\",\n" + 
	"            \"originlocalized\": \"Baile Bhlainséir\",\n" + 
	"            \"direction\": \"Inbound\",\n" + 
	"            \"operator\": \"bac\",\n" + 
	"            \"additionalinformation\": \"\",\n" + 
	"            \"lowfloorstatus\": \"no\",\n" + 
	"            \"route\": \"37\",\n" + 
	"            \"sourcetimestamp\": \"15/04/2015 16:16:21\",\n" + 
	"            \"monitored\": \"true\"\n" + 
	"        }\n" + 
	"    ]\n" + 
	"}";
	private final String TEST_JSON_2=
	"{\n" + 
	"    \"errorcode\": \"0\",\n" + 
	"    \"errormessage\": \"\",\n" + 
	"    \"numberofresults\": 54,\n" + 
	"    \"timestamp\": \"15/04/2015 16:47:33\",\n" + 
	"    \"results\": [\n" + 
	"        {\n" + 
	"            \"stopid\": \"LUAS1\",\n" + 
	"            \"displaystopid\": \"LUAS Tallaght\",\n" + 
	"            \"shortname\": \"\",\n" + 
	"            \"shortnamelocalized\": \"\",\n" + 
	"            \"fullname\": \"LUAS Tallaght\",\n" + 
	"            \"fullnamelocalized\": \"\",\n" + 
	"            \"latitude\": \"53.28749444\",\n" + 
	"            \"longitude\": \"-6.374588889\",\n" + 
	"            \"lastupdated\": \"13/04/2015 08:55:24\",\n" + 
	"            \"operators\": [\n" + 
	"                {\n" + 
	"                    \"name\": \"LUAS\",\n" + 
	"                    \"routes\": [\n" + 
	"                        \"Red\"\n" + 
	"                    ]\n" + 
	"                }\n" + 
	"            ]\n" + 
	"        }" +
	"    ]\n" + 
	"}";
	
	private final String TEST_JSON_3=
	"{\n" + 
	"    \"errorcode\": \"0\",\n" + 
	"    \"errormessage\": \"\",\n" + 
	"    \"numberofresults\": 1,\n" + 
	"    \"timestamp\": \"15/04/2015 16:58:50\",\n" + 
	"    \"results\": [\n" + 
	"        {\n" + 
	"            \"stopid\": \"1686\",\n" + 
	"            \"displaystopid\": \"1686\",\n" + 
	"            \"shortname\": \"Laurel Lodge Rd\",\n" + 
	"            \"shortnamelocalized\": \"Br Lóiste an Labhra\",\n" + 
	"            \"fullname\": \"Parklands\",\n" + 
	"            \"fullnamelocalized\": \"\",\n" + 
	"            \"latitude\": \"53.378812\",\n" + 
	"            \"longitude\": \"-6.372531\",\n" + 
	"            \"lastupdated\": \"22/03/2015 20:23:34\",\n" + 
	"            \"operators\": [\n" + 
	"                {\n" + 
	"                    \"name\": \"bac\",\n" + 
	"                    \"routes\": [\n" + 
	"                        \"37\"\n" + 
	"                    ]\n" + 
	"                }\n" + 
	"            ]\n" + 
	"        }\n" + 
	"    ]\n" + 
	"}";
	
	protected void setUp(){
		parser = new RtpiJsonParser();
	}
	
	public void testCase1(){
		// test ability to reach correct stop info object
		ArrayList<StopInfo> stopInfos = parser.getStopInfo(TEST_JSON_1);
		assertTrue(stopInfos.size() == 1);
		StopInfo stopInfo = stopInfos.get(0);
		assertTrue(stopInfo.getDueTime().equals(EXPECTED_DUE_TIME));
		assertTrue(stopInfo.getDestination().equals(EXPECTED_DESTINATON));
		assertTrue(stopInfo.getRouteId().equals(EXPECTED_ROUTE));
		assertTrue(stopInfo.getArrivalTime().equals(EXPECTED_ARRIVAL_TIME));
		assertTrue(stopInfo.getScheduledArrivalTime().equals(EXPECTED_SCH_ARRIVAL_TIME));
		assertTrue(stopInfo.getServerTime().equals(EXPECTED_SERVER_TIME));
	}
	
	public void testCase2(){
		// test ability to create correct stop object
		ArrayList<Stop> stops = parser.getStops(TEST_JSON_2);
		assertTrue(stops.size() == 1);
		Stop stop = stops.get(0);
		assertTrue(stop.getID().equals(EXPECTED_STOP_ID));
		assertTrue(stop.getName().equals(EXPECTED_STOP_NAME));
	}
	
	public void testCase3(){
		// test ability to create correct location object
		Location location = parser.getStopLocation(TEST_JSON_3);
		assert(location != null);
		assertTrue(location.getLatitude() == EXPECTED_LATITUDE);
		assertTrue(location.getLongitude() == EXPECTED_LONGITUDE);
	}
}
