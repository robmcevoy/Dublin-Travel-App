package com.example.dublintravel;

import android.content.res.TypedArray;
import android.graphics.Color;
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
		int[] attrs = {android.R.attr.background};
		TypedArray ta = controller.getActivity().obtainStyledAttributes(Theme.getCurrentTheme(), attrs);
		int backgroundColor = ta.getColor(0, Color.BLACK);
    	return Integer.toHexString(backgroundColor);
    }
	
	@JavascriptInterface
	public boolean isDarkTheme(){
		return Theme.isDarkTheme();
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
