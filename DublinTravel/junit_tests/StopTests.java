import junit.framework.TestCase;

import com.example.dublintravel.Location;
import com.example.dublintravel.Stop;


public class StopTests extends TestCase {
	
	private Stop stop;
	private final String STOP_NAME = "test name";
	private final String STOP_ID = "1";
	private final double LONGITUDE = 1.4;
	private final double LATITUDE = 3.1;
	private final String ERROR_MESSAGE = "error message";
	private final Stop TO_COMPARE = new Stop("2", STOP_NAME);
	private final Stop TO_COMPARE_2 = new Stop("2", "differnet name");
	
	protected void setUp() {
		stop = new Stop(STOP_ID, STOP_NAME);
	}
	
	public void testCase1(){
		// constructor test case
		assertTrue(stop.getID().equals(STOP_ID));
		assertTrue(stop.getName().equals(STOP_NAME));
		assertTrue(stop.isFavourite() == false);
		assertTrue(stop.hasLocation() == false);
	}
	
	public void testCase2(){
		// favoriting
		stop.favourite();
		assertTrue(stop.isFavourite() == true);
	}
	
	public void testCase3(){
		// unfavoriting
		stop.unfavourite();
		assertTrue(stop.isFavourite() == false);
	}
	
	public void testCase4(){
		//set Location
		stop.setLocation(new Location(LATITUDE, LONGITUDE));
		assertTrue(stop.hasLocation() == true);
		assertTrue(stop.getLocation().getLatitude() == LATITUDE);
		assertTrue(stop.getLocation().getLongitude() == LONGITUDE);
	}
	
	public void testCase5(){
		// set errorMessage
		stop.setErrorMessage(ERROR_MESSAGE);
		assertTrue(stop.getName().equals(ERROR_MESSAGE));
	}
	
	public void testCase6(){
		// comparing stops
		assertTrue(!stop.equals(TO_COMPARE));
		assertTrue(!stop.equals(TO_COMPARE_2));
		assertTrue(stop.equals(stop));
	}

}
