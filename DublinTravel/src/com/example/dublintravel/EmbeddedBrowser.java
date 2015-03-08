package com.example.dublintravel;

import android.graphics.Color;
import android.os.Build;
import android.webkit.WebView;

public abstract class EmbeddedBrowser {
	
	protected WebView webview;
	protected String url = "";
	
	public EmbeddedBrowser(WebView webview){
		this.webview = webview;
	}
	
	
	public void start(){
		// Fixes flash of white bug 
		webview.setBackgroundColor(Color.argb(1, 0, 0, 0));
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setSupportMultipleWindows(true);
		if( hasWebViewInterface()){
			webview.addJavascriptInterface(getWebviewInterface(), getWebviewInterface().getInterfaceName());
		}
		webview.loadUrl(url);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}
	
	public void reload(){
		webview.reload();
	}
	
	public abstract boolean hasWebViewInterface();
	public abstract WebViewInterface getWebviewInterface();

}
