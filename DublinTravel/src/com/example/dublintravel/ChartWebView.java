package com.example.dublintravel;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;

public class ChartWebView {
	
	Adapter adapter;
	WebView webview;
	Context context;
	RtpiController rtpiController;

	ChartWebView(WebView webview, Adapter adapter, RtpiController rtpiController){
		this.adapter = adapter;
		this.webview = webview;
		this.rtpiController = rtpiController;
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.addJavascriptInterface(new WebAppInterface(), "Android");
		webview.loadUrl("file:///android_asset/am_charts.html");
		webview.setWebViewClient(new WebViewClient() {

	        public void onReceivedError(WebView view, int errorCode,
	                String description, String failingUrl) {
	            // Handle the error
	        }

	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	    }});
	}
	
	public class WebAppInterface {

	     @JavascriptInterface
	  public String getOperator() {
	    	 return rtpiController.getCurrentOperator().getOperatorCode();
	  }
	}
}
