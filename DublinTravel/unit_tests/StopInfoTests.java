import junit.framework.TestCase;
import com.example.dublintravel.StopInfo;


public class StopInfoTests extends TestCase {
	
	private StopInfo stopInfo;
	private final String ROUTE_ID = "1";
	private final String DESTINATION = "test destination";
	private final String DUE_TIME = "2";
	private final String ARRIVAL_TIME = "15/04/2015 16:42:24";
	private final String SERVER_TIME = "15/04/2015 16:24:27";
	private final String SCH_ARRIVAL_TIME = "15/04/2015 16:41:00";
	private final int EXPECTED_DIFF_IN_MINS = 1;
	private final int EXPECTED_DUE_TIME = 2;
	
	private final StopInfo TO_COMPARE_1 = new StopInfo(ROUTE_ID, DESTINATION, "3", ARRIVAL_TIME, SCH_ARRIVAL_TIME, SERVER_TIME);
	private final StopInfo TO_COMPARE_2 = new StopInfo(ROUTE_ID, DESTINATION, "0", ARRIVAL_TIME, SCH_ARRIVAL_TIME, SERVER_TIME);
	
	protected void setUp(){
		this.stopInfo = new StopInfo(ROUTE_ID, DESTINATION, DUE_TIME, ARRIVAL_TIME, SCH_ARRIVAL_TIME, SERVER_TIME);
	}
	
	public void testCase1(){
		// test constructor
		assertTrue(stopInfo.getRouteId().equals(ROUTE_ID));
		assertTrue(stopInfo.getDestination().equals(DESTINATION));
		assertTrue(stopInfo.getDueTime().equals(DUE_TIME));
		assertTrue(stopInfo.getArrivalTime().equals(ARRIVAL_TIME));
		assertTrue(stopInfo.getServerTime().equals(SERVER_TIME));
		assertTrue(stopInfo.getScheduledArrivalTime().equals(SCH_ARRIVAL_TIME));
	}
	
	public void testCase2(){
		// get time difference
		assertTrue(stopInfo.getDiffInMins() == EXPECTED_DIFF_IN_MINS);
	}
	
	public void testCase3(){
		// get duetime as int
		assertTrue(stopInfo.getDueTimeAsInt() == EXPECTED_DUE_TIME);
	}
	
	public void testCase4(){
		// comparing stops
		assertTrue(stopInfo.compareTo(TO_COMPARE_1) == -1);
		assertTrue(stopInfo.compareTo(TO_COMPARE_2) == 1);
		assertTrue(stopInfo.compareTo(stopInfo) == 0);
	}

}
