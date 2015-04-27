import com.example.dublintravel.IrishRailXmlParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import com.example.dublintravel.StopInfo;
import com.example.dublintravel.Stop;


public class IrishRailXmlParserTests extends TestCase {
	
	private IrishRailXmlParser parser;
	private final String EXPECTED_DUE_TIME= "3";
	private final String EXPECTED_DESTINATON= "M3 Parkway";
	private final String EXPECTED_ROUTE= "Northbound";
	private final String DATE_FORMAT= "dd/MM/yyyy";
	private final DateFormat DF = new SimpleDateFormat(DATE_FORMAT);
	private final Date TODAY = Calendar.getInstance().getTime();
	private final String DATE_STING = DF.format(TODAY);
	private final String EXPECTED_ARRIVAL_TIME= DATE_STING + " " + "17:10:00";
	private final String EXPECTED_SCH_ARRIVAL_TIME= DATE_STING + " " + "17:09:00";
	private final String EXPECTED_SERVER_TIME= "2015-04-15T17:08:56.22";
	private final String EXPECTED_STOP_ID= "BFSTC";
	private final String EXPECTED_STOP_NAME= "Belfast Central";
	private final double EXPECTED_LATITUDE= 54.6123;
	private final double EXPECTED_LONGITUDE= -5.91744;
	
	protected void setUp() {
		parser = new IrishRailXmlParser();
	}
	
	private final String TEST_XML_1=
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
			"<ArrayOfObjStationData \n" + 
			"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" + 
			"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" + 
			"    xmlns=\"http://api.irishrail.ie/realtime/\">\n" + 
			"    <objStationData>\n" + 
			"        <Servertime>2015-04-15T17:08:56.22</Servertime>\n" + 
			"        <Traincode>D312 </Traincode>\n" + 
			"        <Stationfullname>Castleknock</Stationfullname>\n" + 
			"        <Stationcode>CNOCK</Stationcode>\n" + 
			"        <Querytime>17:08:56</Querytime>\n" + 
			"        <Traindate>15 Apr 2015</Traindate>\n" + 
			"        <Origin>Docklands</Origin>\n" + 
			"        <Destination>M3 Parkway</Destination>\n" + 
			"        <Origintime>16:55</Origintime>\n" + 
			"        <Destinationtime>17:28</Destinationtime>\n" + 
			"        <Status>En Route</Status>\n" + 
			"        <Lastlocation>Departed Ashtown</Lastlocation>\n" + 
			"        <Duein>3</Duein>\n" + 
			"        <Late>2</Late>\n" + 
			"        <Exparrival>17:10</Exparrival>\n" + 
			"        <Expdepart>17:11</Expdepart>\n" + 
			"        <Scharrival>17:09</Scharrival>\n" + 
			"        <Schdepart>17:09</Schdepart>\n" + 
			"        <Direction>Northbound</Direction>\n" + 
			"        <Traintype>Train</Traintype>\n" + 
			"        <Locationtype>S</Locationtype>\n" + 
			"    </objStationData>\n" + 
			"</ArrayOfObjStationData>";
	
	private final String TEST_XML_2=
	"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
	"<ArrayOfObjStation \n" + 
	"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" + 
	"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" + 
	"    xmlns=\"http://api.irishrail.ie/realtime/\">\n" + 
	"    <objStation>\n" + 
	"        <StationDesc>Belfast Central</StationDesc>\n" + 
	"        <StationAlias />\n" + 
	"        <StationLatitude>54.6123</StationLatitude>\n" + 
	"        <StationLongitude>-5.91744</StationLongitude>\n" + 
	"        <StationCode>BFSTC</StationCode>\n" + 
	"        <StationId>228</StationId>\n" + 
	"    </objStation>" +
	"</ArrayOfObjStation>";
	
	public void testCase1(){
		// test ability to reach correct stop info object
		ArrayList<StopInfo> stopInfos = parser.getStopInfo(TEST_XML_1);
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
		ArrayList<Stop> stops = parser.getStops(TEST_XML_2);
		assertTrue(stops.size() == 1);
		Stop stop = stops.get(0);
		assertTrue(stop.getID().equals(EXPECTED_STOP_ID));
		assertTrue(stop.getName().equals(EXPECTED_STOP_NAME));
		assertTrue(stop.getLocation().getLatitude() == EXPECTED_LATITUDE);
		assertTrue(stop.getLocation().getLongitude() == EXPECTED_LONGITUDE);
	}
	
}
