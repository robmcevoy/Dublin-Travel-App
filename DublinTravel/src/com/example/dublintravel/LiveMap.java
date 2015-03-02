package com.example.dublintravel;

import android.webkit.WebView;

public class LiveMap extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/live_map/live_map.html";
	
	public LiveMap(WebView webview){
		super(webview);
		this.url = URL;
	}

	public boolean hasWebViewInterface() {
		return false;
	}

	@Override
	public WebviewInterface getWebviewInterface() {
		return null;
	}
	
	

}
