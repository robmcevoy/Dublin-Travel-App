package com.example.dublintravel;

import java.util.ArrayList;

import android.webkit.JavascriptInterface;

public class WebviewInterface {
	
	private boolean firstCall;
	private RtpiController rtpiController;
	private ArrayList<StopInfo> stopInfoArray;
	private final String INTERFACE_NAME = "Android";
	
	public WebviewInterface(RtpiController rtpiController){
		this.rtpiController = rtpiController;
		this.firstCall = true;
		this.stopInfoArray = new ArrayList<StopInfo>();
	}
	
	public String getInterfaceName(){
		return INTERFACE_NAME;
	}
	
	 @JavascriptInterface
	 public String getOperator() {
    	 return rtpiController.getCurrentOperator().getOperatorCode();
     }
     @JavascriptInterface 
     public int getStopInfoCount(){
    	 if(firstCall){
    		 stopInfoArray = rtpiController.getStopInfos();
    		 firstCall = false;
    	 }
    	 if(stopInfoArray != null){
    		 return stopInfoArray.size();
    	 }
    	 else{
    		 return 0;
    	 }
     }
     @JavascriptInterface
     public String getDueDate(int index){	
    	 if(stopInfoArray != null){
    		 return stopInfoArray.get(index).getArrivalTime();
    	 }
    	 else{
    		 return "";
    	 }
     }
     
     @JavascriptInterface
     public int getDifference(int index){	
    	 ArrayList<StopInfo> tmp;
    	 tmp = rtpiController.getStopInfos();
    	 if(tmp != null){
    		return  tmp.get(index).getDiffInMins();
    	 }
    	 else{
    		 return 0;
    	 }
     }
     
     public void reload(){
    	 firstCall=true;
     }

}
