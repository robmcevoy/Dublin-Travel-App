package com.example.dublintravel;

import android.content.Context;
import android.webkit.WebView;

public class LiveMap extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/live_map/live_map.html";
	private LiveMapWebViewInterface webInterface;
	private Context context;
	
	public LiveMap(WebView webview, Context context){
		super(webview);
		this.context = context;
		this.url = URL;
		webInterface = new LiveMapWebViewInterface(this.context);
	}

	public boolean hasWebViewInterface() {
		return true;
	}

	@Override
	public LiveMapWebViewInterface getWebviewInterface() {
		return webInterface;
	}
}
