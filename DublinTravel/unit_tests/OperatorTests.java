import java.util.ArrayList;
import junit.framework.TestCase;
import com.example.dublintravel.IrishRailOperator;
import com.example.dublintravel.DublinBusOperator;
import com.example.dublintravel.LuasOperator;
import com.example.dublintravel.BusEireannOperator;
import com.example.dublintravel.Operator;
import com.example.dublintravel.Stop;


public class OperatorTests extends TestCase {
	
	private DublinBusOperator dublinBusOp;
	private IrishRailOperator irishRailOp;
	private LuasOperator luasOp;
	private BusEireannOperator busEireannOp;
	private final String STOP_ID="1";
	private final int NUM_POSSIBLE_OPERATORS=4;
	private final Stop TEST_STOP = new Stop(STOP_ID, "test stop");
	private final String DUBLIN_BUS_STOPS_URL = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=bac&format=json";
	private final String BUS_EIREANN_STOPS_URL = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=be&format=json";
	private final String LUAS_STOPS_URL = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?operator=luas&format=json";
	private final String IRISH_RAIL_STOPS_URL = "http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML ";
	private final String DUBLIN_BUS_STOP_REALTIME_URL = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=1&operator=bac&format=json";
	private final String BUS_EIREANN_STOP_REALTIME_URL = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=1&operator=be&format=json";
	private final String LUAS_STOP_REALTIME_URL = "http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid=1&operator=luas&format=json";
	private final String IRISH_RAIL_STOP_REALTIME_URL = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=1";
	private final String LOCATION_REQUEST = "http://www.dublinked.ie/cgi-bin/rtpi/busstopinformation?stopid=1";
	
	protected void setUp(){
		dublinBusOp = new DublinBusOperator();
		irishRailOp = new IrishRailOperator();
		luasOp = new LuasOperator();
		busEireannOp = new BusEireannOperator();
	}
	
	public void testCase1(){
		// activate
		assertTrue(!dublinBusOp.isActive());
		assertTrue(!irishRailOp.isActive());
		assertTrue(!luasOp.isActive());
		assertTrue(!busEireannOp.isActive());
		dublinBusOp.activate();
		irishRailOp.activate();
		luasOp.activate();
		busEireannOp.activate();
		assertTrue(dublinBusOp.isActive());
		assertTrue(irishRailOp.isActive());
		assertTrue(luasOp.isActive());
		assertTrue(busEireannOp.isActive());
	}
	
	public void testCase2(){
		// deactivate
		dublinBusOp.deactivate();
		irishRailOp.deactivate();
		luasOp.deactivate();
		busEireannOp.deactivate();
		assertTrue(!dublinBusOp.isActive());
		assertTrue(!irishRailOp.isActive());
		assertTrue(!luasOp.isActive());
		assertTrue(!busEireannOp.isActive());
	}
	
	public void testCase3(){
		// equals
		assertTrue(dublinBusOp.equals(dublinBusOp));
		assertTrue(!dublinBusOp.equals(luasOp));
		assertTrue(luasOp.equals(luasOp));
		assertTrue(!luasOp.equals(irishRailOp));
		assertTrue(irishRailOp.equals(irishRailOp));
		assertTrue(!irishRailOp.equals(busEireannOp));
		assertTrue(busEireannOp.equals(busEireannOp));
		assertTrue(!busEireannOp.equals(dublinBusOp));
	}
	
	public void testCase4(){
		// generating get stops url
		assertTrue(dublinBusOp.generateStopsUrl().equals(DUBLIN_BUS_STOPS_URL));
		assertTrue(busEireannOp.generateStopsUrl().equals(BUS_EIREANN_STOPS_URL));
		assertTrue(luasOp.generateStopsUrl().equals(LUAS_STOPS_URL));
		assertTrue(irishRailOp.generateStopsUrl().equals(IRISH_RAIL_STOPS_URL));
	}
	
	public void testCase5(){
		// generating get realtime info for stop url
		assertTrue(dublinBusOp.generateRealtimeInfoUrlString(STOP_ID).equals(DUBLIN_BUS_STOP_REALTIME_URL));
		assertTrue(busEireannOp.generateRealtimeInfoUrlString(STOP_ID).equals(BUS_EIREANN_STOP_REALTIME_URL));
		assertTrue(luasOp.generateRealtimeInfoUrlString(STOP_ID).equals(LUAS_STOP_REALTIME_URL));
		assertTrue(irishRailOp.generateRealtimeInfoUrlString(STOP_ID).equals(IRISH_RAIL_STOP_REALTIME_URL));
	}
	
	public void testCase6(){
		// generating get location requests
		assertTrue(dublinBusOp.generateStopLocationUrl(STOP_ID).equals(LOCATION_REQUEST));
		assertTrue(busEireannOp.generateStopLocationUrl(STOP_ID).equals(LOCATION_REQUEST));
		assertTrue(luasOp.generateStopLocationUrl(STOP_ID).equals(LOCATION_REQUEST));
		assertTrue(irishRailOp.generateStopLocationUrl(STOP_ID).equals(""));
	}
	
	public void testCase7(){
		// test each operator has a unique index
		ArrayList<Operator> operators = new ArrayList<Operator>();
		operators.add(busEireannOp);
		operators.add(dublinBusOp);
		operators.add(irishRailOp);
		operators.add(luasOp);
		boolean foundMatch = false;
		for(Operator tmp: operators){
			for(Operator tmp1: operators){
				if(operators.indexOf(tmp) != operators.indexOf(tmp1)){
					foundMatch = tmp.getIndex() ==  tmp1.getIndex();
				}
			}
		}
		assertTrue(!foundMatch);
	}
	
	public void testCase8(){
		// requires additional requests
		assertTrue(dublinBusOp.requireAdditionalLocationRequest());
		assertTrue(luasOp.requireAdditionalLocationRequest());
		assertTrue(busEireannOp.requireAdditionalLocationRequest());
		assertTrue(!irishRailOp.requireAdditionalLocationRequest());
	}
	
	public void testCase9(){
		// needs auth
		assertTrue(dublinBusOp.needsAuth());
		assertTrue(luasOp.needsAuth());
		assertTrue(busEireannOp.needsAuth());
		assertTrue(!irishRailOp.needsAuth());
	}
	
	public void testCase10(){
		// setting stops
		assertTrue(!dublinBusOp.hasPreviousStop());
		assertTrue(!luasOp.hasPreviousStop());
		assertTrue(!busEireannOp.hasPreviousStop());
		assertTrue(!irishRailOp.hasPreviousStop());
		dublinBusOp.setPreviousStop(TEST_STOP);
		luasOp.setPreviousStop(TEST_STOP);
		busEireannOp.setPreviousStop(TEST_STOP);
		irishRailOp.setPreviousStop(TEST_STOP);
		assertTrue(dublinBusOp.hasPreviousStop());
		assertTrue(luasOp.hasPreviousStop());
		assertTrue(busEireannOp.hasPreviousStop());
		assertTrue(irishRailOp.hasPreviousStop());
	}
	
	public void testCase11(){
		// getting number of possible operators
		assertTrue(dublinBusOp.getNumPossibleOperators() == NUM_POSSIBLE_OPERATORS);
		assertTrue(luasOp.getNumPossibleOperators() == NUM_POSSIBLE_OPERATORS);
		assertTrue(busEireannOp.getNumPossibleOperators() == NUM_POSSIBLE_OPERATORS);
		assertTrue(irishRailOp.getNumPossibleOperators() == NUM_POSSIBLE_OPERATORS);
	}
}
