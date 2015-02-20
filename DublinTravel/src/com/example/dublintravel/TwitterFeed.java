package com.example.dublintravel;

import android.webkit.WebView;

public class TwitterFeed extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/twitter_feed/twitter_feed.html";

	public TwitterFeed(WebView webview, RtpiController rtpiController){
		super(webview, rtpiController);
		this.url = URL;
	}

}
