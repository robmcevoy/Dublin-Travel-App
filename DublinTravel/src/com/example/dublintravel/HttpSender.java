package com.example.dublintravel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
// Helper class to aid in the process of sending http requests to rest apis

public class HttpSender {
	
	// only calls to the RTPI dub linked services need authentication
	// calls to Irish rail do not
	private boolean needsAuth;
	
	private final String AUTHENTICATION =  "Basic cm9ibWNldm95OjE4MDltY2V2b3kyMDE0";
	
	public String sendGetRequest(String address, boolean needsAuth1){
		// sends a http request and returns the response as a string		
		needsAuth = needsAuth1;
		String result="nothing";
		URL url;
		try {
			url = new URL(address);
			URLConnection uc = url.openConnection();
			if(needsAuth){
				uc.setRequestProperty ("Authorization", AUTHENTICATION);
			}
			InputStream in = uc.getInputStream();
			result = convertInputStreamToString(in);
		} catch (Exception e) {
			return "exception in http sender: " + e.toString();
		}
		return result;
	}
	
	
	
	// convert an input stream to a string
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
    }

}
