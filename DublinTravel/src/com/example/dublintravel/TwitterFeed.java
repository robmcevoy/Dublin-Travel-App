package com.example.dublintravel;

import android.webkit.WebView;

public class TwitterFeed extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/twitter_feed/twitter_feed.html";
	private WebViewInterface webInterface;

	public TwitterFeed(WebView webview, RtpiController rtpiController){
		super(webview);
		this.url = URL;
		webInterface = new RtpiWebViewInterface(rtpiController);
	}
	
	public TwitterFeed(WebView webview, LiveMapController liveMapController){
		super(webview);
		this.url = URL;
		webInterface = new LiveMapWebViewInterface(liveMapController);
	}

	public boolean hasWebViewInterface() {
		return true;
	}

	public WebViewInterface getWebviewInterface() {
		return webInterface;
	}

}
