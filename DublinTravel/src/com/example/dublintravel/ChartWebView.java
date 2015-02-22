package com.example.dublintravel;

import android.webkit.WebView;

public class ChartWebView extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/chart/am_charts.html";

	public ChartWebView(WebView webview, RtpiController rtpiController){
		super(webview, rtpiController);
		this.url = URL;
	}

}
