package com.example.dublintravel;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class LiveMap extends EmbeddedBrowser {
	
	private final String URL = "file:///android_asset/live_map/live_map.html";
	private LiveMapWebViewInterface webInterface;
	private LiveMapController liveMapController;
	
	public LiveMap(WebView webview, final LiveMapController liveMapController){
		super(webview);
		this.url = URL;
		this.liveMapController = liveMapController;
		webInterface = new LiveMapWebViewInterface(this.liveMapController);
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
	public LiveMapWebViewInterface getWebviewInterface() {
		return webInterface;
	}
}
