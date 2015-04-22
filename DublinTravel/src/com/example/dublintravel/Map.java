package com.example.dublintravel;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Map extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/live_map/live_map.html";
	private MapWebViewInterface webInterface;
	private MapDashboardController liveMapController;
	
	public Map(WebView webview, final MapDashboardController liveMapController){
		super(webview);
		this.url = URL;
		this.liveMapController = liveMapController;
		webInterface = new MapWebViewInterface(this.liveMapController);
		this.webview.setWebChromeClient(new WebChromeClient() {
			 public void onGeolocationPermissionsShowPrompt(String origin, android.webkit.GeolocationPermissions.Callback callback) {
			    callback.invoke(origin, true, false);
			 }
		});
	}

	public boolean hasWebViewInterface() {
		return true;
	}

	@Override
	public MapWebViewInterface getWebviewInterface() {
		return webInterface;
	}
}
