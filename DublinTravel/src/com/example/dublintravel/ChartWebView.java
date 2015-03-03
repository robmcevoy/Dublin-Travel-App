package com.example.dublintravel;

import android.webkit.WebView;

public class ChartWebView extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/chart/am_charts.html";
	private RtpiWebViewInterface webInterface;

	public ChartWebView(WebView webview, RtpiController rtpiController){
		super(webview);
		this.url = URL;
		webInterface = new RtpiWebViewInterface(rtpiController);
	}
	
	public boolean hasWebViewInterface() {
		return true;
	}

	public WebViewInterface getWebviewInterface() {
		return webInterface;
	}

}
