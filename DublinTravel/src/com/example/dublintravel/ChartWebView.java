package com.example.dublintravel;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ChartWebView extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/chart/am_charts.html";
	private PTDWebViewInterface webInterface;
	public static final String LOG_TAG = "CHART_LOG";
	private JSONObject testData;

	public ChartWebView(WebView webview, PTDController rtpiController){
		super(webview);
		this.url = URL;
		webInterface = new PTDWebViewInterface(rtpiController);
		setConsoleLog();
	}
	
	public boolean hasWebViewInterface() {
		return true;
	}

	public WebViewInterface getWebviewInterface() {
		return webInterface;
	}
	
	private void setConsoleLog(){
		webview.setWebChromeClient(new WebChromeClient() {
			public void onConsoleMessage(String message, int lineNumber, String sourceID) {
			    Log.d(LOG_TAG, message);
			    if(message.contains("backgroundColor")){
			    	try {
			    		testData = new JSONObject(message);
			    	} catch (JSONException e) {}
			    }
			  }
		});
	}
	
	public JSONObject getTestData(){
		return testData;
	}
}
