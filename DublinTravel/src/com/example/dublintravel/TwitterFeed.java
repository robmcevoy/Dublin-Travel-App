package com.example.dublintravel;

import android.webkit.WebView;

public class TwitterFeed extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/twitter_feed/twitter_feed.html";
	private WebViewInterface webInterface;

	public TwitterFeed(WebView webview, PTDController rtpiController){
		super(webview);
		this.url = URL;
		webInterface = new PTDWebViewInterface(rtpiController);
	}
	
	public TwitterFeed(WebView webview, MapDashboardController liveMapController){
		super(webview);
		this.url = URL;
		webInterface = new MapWebViewInterface(liveMapController);
	}

	public boolean hasWebViewInterface() {
		return true;
	}

	public WebViewInterface getWebviewInterface() {
		return webInterface;
	}

}
