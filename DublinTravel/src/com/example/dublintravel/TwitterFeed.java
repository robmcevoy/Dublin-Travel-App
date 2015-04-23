package com.example.dublintravel;

import android.webkit.WebView;

public class TwitterFeed extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/twitter_feed/twitter_feed.html";
	private WebViewInterface webInterface;

	public TwitterFeed(WebView webview, PTDController controller){
		super(webview);
		this.url = URL;
		webInterface = new PTDWebViewInterface(controller);
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
