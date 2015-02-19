package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;

public class ChartWebView {
	
	WebView webview;
	RtpiController rtpiController;
	ArrayList<StopInfo> stopInfoArray;
	boolean firstCall;

	ChartWebView(WebView webview, RtpiController rtpiController){
		this.webview = webview;
		this.rtpiController = rtpiController;
		this.stopInfoArray = new ArrayList<StopInfo>();
		this.firstCall = true;
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.addJavascriptInterface(new WebAppInterface(), "Android");
		webview.loadUrl("file:///android_asset/chart/am_charts.html");
		webview.setWebViewClient(new WebViewClient() {

	        public void onReceivedError(WebView view, int errorCode,
	                String description, String failingUrl) {}

	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	    }});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}
	
	public void reload(){
		webview.reload();
		firstCall = true;
	}
	
	public class WebAppInterface {

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
	     public int getDueTime(int index){	
	    	 if(stopInfoArray != null){
	    		 return stopInfoArray.get(index).getDueTimeAsInt();
	    	 }
	    	 else{
	    		 return 0;
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
	    
	}
}
