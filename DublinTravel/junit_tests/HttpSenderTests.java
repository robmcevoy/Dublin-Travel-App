import junit.framework.TestCase;
import com.example.dublintravel.HttpSender;


public class HttpSenderTests extends TestCase {
	
	private final String[] TEST_URLS={	"http://example.com/", 
										"http://www.dublinked.ie/cgi-bin/rtpi/routelistinformation?operator=bac&format=json",
										""
	};
	private HttpSender httpSender;
	private final boolean[] NEEDS_AUTHS= { false, true};
	private final String[] RESPONSE_CONTAINS = {	"Example Domain",
											"timestamp",
	};
	
	protected void setUp() {
		httpSender = new HttpSender();
	}
	
	public void testCase1(){
		// without authentication
		String response = httpSender.sendGetRequest(TEST_URLS[0], NEEDS_AUTHS[0]);
		assertTrue(response.contains(RESPONSE_CONTAINS[0]));
	}
	
	public void testCase2(){
		// with authentication
		String response = httpSender.sendGetRequest(TEST_URLS[1], NEEDS_AUTHS[1]);
		assertTrue(response.contains(RESPONSE_CONTAINS[1]));
	}
	
	public void testCase3(){
		// empty url
		String response = httpSender.sendGetRequest("", NEEDS_AUTHS[0]);
		assertTrue(response == null);

	}
	
	public void testCase4(){
		// needs auth but need given
		String response = httpSender.sendGetRequest(TEST_URLS[1], NEEDS_AUTHS[0]);
		assertTrue(response == null);
	}
}