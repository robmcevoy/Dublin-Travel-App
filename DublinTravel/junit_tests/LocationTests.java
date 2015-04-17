import junit.framework.TestCase;
import com.example.dublintravel.Location;


public class LocationTests extends TestCase {
	
	private Location location;
	private final double LATITUDE= 1.0;
	private final double LONGITUDE= -1.0;
	private final String LATITUDE_STRING= "2.0";
	private final String LONGITUDE_STRING= "-2.0";
	private final double LATITUDE_STRING_DOUBLE= 2.0;
	private final double LONGITUDE_STRING_DOUBLE= -2.0;
	
	protected void setUp(){
		location = new Location(LATITUDE, LONGITUDE);
	}
	
	public void testCase1(){
		//constructor
		assertTrue(location.getLatitude() == LATITUDE);
		assertTrue(location.getLongitude() == LONGITUDE);
	}
	
	public void testCase2(){
		// set location & latitude using strings
		location.setLatitude(LATITUDE_STRING);
		assertTrue(location.getLatitude() == LATITUDE_STRING_DOUBLE);
		location.setLongitude(LONGITUDE_STRING);
		assertTrue(location.getLongitude() == LONGITUDE_STRING_DOUBLE);	
	}

}
