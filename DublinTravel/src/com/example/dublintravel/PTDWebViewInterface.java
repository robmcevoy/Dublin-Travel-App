package com.example.dublintravel;

import java.util.ArrayList;
import android.webkit.JavascriptInterface;

/* creates a javascript interface for the public transport dashboard chart visualizations
 * inherits some other interface functions from WebViewInterface
 */

public class PTDWebViewInterface extends WebViewInterface{
	
	private ArrayList<StopInfo> stopInfoArray;
	private final int MAX_NUM_ON_CHART = 5;
	private PTDController controller;
	
	public PTDWebViewInterface(PTDController controller){
		super(controller);
		this.controller = controller;
		this.stopInfoArray = new ArrayList<StopInfo>();
	}
	
	 @JavascriptInterface
	 public String getOperator() {
    	 return controller.getCurrentOperator().getOperatorCode();
     }
     @JavascriptInterface 
     public int getStopInfoCount(){
    	 stopInfoArray = controller.getStopInfos();
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
     public String getServerTime(){
    	 return controller.getServerTime();
     }

}
