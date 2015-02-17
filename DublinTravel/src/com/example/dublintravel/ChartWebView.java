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
	Context context;
	RtpiController rtpiController;

	ChartWebView(WebView webview, RtpiController rtpiController){
		this.webview = webview;
		this.rtpiController = rtpiController;
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.addJavascriptInterface(new WebAppInterface(), "Android");
		webview.loadUrl("file:///android_asset/am_charts.html");
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
	}
	
	public class WebAppInterface {

	     @JavascriptInterface
	     public String getOperator() {
	    	 return rtpiController.getCurrentOperator().getOperatorCode();
	     }
	     @JavascriptInterface 
	     public int getStopInfoCount(){
	    	 ArrayList<StopInfo> tmp;
	    	 tmp = rtpiController.getStopInfos();
	    	 if(tmp != null){
	    		 return tmp.size();
	    	 }
	    	 else{
	    		 return 0;
	    	 }
	     }
	     @JavascriptInterface
	     public int getDueTime(int index){	
	    	 ArrayList<StopInfo> tmp;
	    	 tmp = rtpiController.getStopInfos();
	    	 if(tmp != null){
	    		 return tmp.get(index).getDueTimeAsInt();
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
