package com.example.dublintravel;

import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmbeddedBrowser {
	
	private WebView webview;
	private RtpiController rtpiController;
	private WebviewInterface webviewInterface;
	protected String url = "file:///android_asset/twitter_feed/twitter_feed.html";
	
	public EmbeddedBrowser(WebView webview, RtpiController rtpiController){
		this.webview = webview;
		this.rtpiController = rtpiController;
		this.webviewInterface = new WebviewInterface(this.rtpiController);
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.addJavascriptInterface(webviewInterface, webviewInterface.getInterfaceName());
		webview.loadUrl(url);
		webview.setWebViewClient(new WebViewClient() {

	        public void onReceivedError(WebView view, int errorCode,
	                String description, String failingUrl) {}

	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	    }});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}
	
	public void reload(){
		webview.reload();
	}

}
