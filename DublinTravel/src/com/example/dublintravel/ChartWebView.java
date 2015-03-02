package com.example.dublintravel;

import android.webkit.WebView;

public class ChartWebView extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/chart/am_charts.html";
	private WebviewInterface webInterface;

	public ChartWebView(WebView webview, RtpiController rtpiController){
		super(webview);
		this.url = URL;
		webInterface = new WebviewInterface(rtpiController);
	}
	
	public boolean hasWebViewInterface() {
		return true;
	}

	public WebviewInterface getWebviewInterface() {
		return webInterface;
	}

}
