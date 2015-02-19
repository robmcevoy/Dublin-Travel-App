package com.example.dublintravel;

import com.example.dublintravel.ChartWebView.WebAppInterface;

import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterFeed {
	
	private WebView webview;
	private RtpiController rtpiController;
	
	TwitterFeed(WebView webview, RtpiController rtpiController){
		this.webview = webview;
		this.rtpiController = rtpiController;
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("file:///android_asset/twitter_feed/twitter_feed.html");
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

}
