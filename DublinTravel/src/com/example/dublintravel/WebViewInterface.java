package com.example.dublintravel;

import android.webkit.JavascriptInterface;

/* The javascript interface for all embedded browsers in the application
 * this class can be extended in order to expose more functions
 */

public class WebViewInterface {
	
	private final String INTERFACE_NAME = "Android";
	protected Controller controller;
	
	public WebViewInterface(Controller controller){
		this.controller = controller;
	}

	public String getInterfaceName(){
		return INTERFACE_NAME;
	}
	
	@JavascriptInterface
    public String getBackgroundColor(){
    	String tmp =controller.getCurrentContext().getResources().getString(R.color.dark_grey);
    	return "#" + tmp.substring(3);
    }
    
    @JavascriptInterface
    public String getSecondaryColor(){
   	 	String tmp = controller.getCurrentContext().getResources().getString(R.color.orange);
   	 	return "#" + tmp.substring(3);
    }
    
    @JavascriptInterface
    public String getDublinBusOpCode(){
   	 	return new DublinBusOperator().getOperatorCode();
    }
    
    @JavascriptInterface
    public String getIrishRailOpCode(){
    	return new IrishRailOperator().getOperatorCode();
    }
    
    @JavascriptInterface
    public String getLuasOpCode(){
   	 	return new LuasOperator().getOperatorCode();
    }
    
    @JavascriptInterface
    public String getBusEireannOpCode(){
   	 	return new BusEireannOperator().getOperatorCode();
    }
}
