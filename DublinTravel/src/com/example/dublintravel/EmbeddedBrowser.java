package com.example.dublintravel;

import android.os.Build;
import android.webkit.WebView;

public class EmbeddedBrowser {
	
	private WebView webview;
	private RtpiController rtpiController;
	private WebviewInterface webviewInterface;
	protected String url = "";
	
	public EmbeddedBrowser(WebView webview, RtpiController rtpiController){
		this.webview = webview;
		this.rtpiController = rtpiController;
		this.webviewInterface = new WebviewInterface(this.rtpiController);
	}
	
	public void start(){
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setSupportMultipleWindows(true);
		webview.addJavascriptInterface(webviewInterface, webviewInterface.getInterfaceName());
		webview.loadUrl(url);
	    
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}
	
	public void reload(){
		webview.reload();
	}

}
