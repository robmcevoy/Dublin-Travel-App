package com.example.dublintravel;

import android.webkit.WebView;

public class TwitterFeed extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/twitter_feed/twitter_feed.html";
	private WebviewInterface webInterface;

	public TwitterFeed(WebView webview, RtpiController rtpiController){
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
