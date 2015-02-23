package com.example.dublintravel;

import java.util.ArrayList;

import android.webkit.JavascriptInterface;

public class WebviewInterface {
	
	private RtpiController rtpiController;
	private ArrayList<StopInfo> stopInfoArray;
	private final String INTERFACE_NAME = "Android";
	private final int MAX_NUM_ON_CHART = 5;
	
	public WebviewInterface(RtpiController rtpiController){
		this.rtpiController = rtpiController;
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
    	 stopInfoArray = rtpiController.getStopInfos();
    	 if(stopInfoArray != null){
    		 return stopInfoArray.size();
    	 }
    	 return 0;

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
    	 if(stopInfoArray != null){
    		return  stopInfoArray.get(index).getDiffInMins();
    	 }
    	 else{
    		 return 0;
    	 }
     }
     
     @JavascriptInterface
	 public int getMaxOnChart(){
    	 return MAX_NUM_ON_CHART;
     }
     
     @JavascriptInterface
     public String getBackgroundColor(){
    	 String tmp =rtpiController.getCurrentContext().getResources().getString(R.color.dark_grey);
    	 return "#" + tmp.substring(3);
     }
     
     @JavascriptInterface
     public String getSecondaryColor(){
    	 String tmp = rtpiController.getCurrentContext().getResources().getString(R.color.orange);
    	 return "#" + tmp.substring(3);
     }

}
