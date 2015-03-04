package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RtpiDashboardActivity extends Activity {

	RtpiController rtpiController;
	RtpiNavigationBar navbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rtpi_dashboard);
		
		// create objects
        final Context context = this;
        final TextView stopTextView = (TextView) findViewById(R.id.stop);
        final ListView stopInfoListView = (ListView) findViewById(R.id.stopInfoListView);
        final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
        final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
        final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
        final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
        final ImageView liveMapImageView = (ImageView) findViewById(R.id.liveMapLogo);
        WebView chartVis = (WebView) findViewById(R.id.webView1);
        WebView twitterFeed = (WebView) findViewById(R.id.twitterFeed);
        navbar = new RtpiNavigationBar(dublinBusImageView,luasImageView, 
		irishRailImageView,busEireannImageView, liveMapImageView);
        rtpiController = new RtpiController(context, navbar, stopTextView, stopInfoListView, chartVis, twitterFeed);
	    
        // handle bundle
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.handleBundle(EXTRAS);   
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		if(rtpiController != null && navbar != null){
			Stop tmpStop = rtpiController.getCurrentStop();
			Operator[] operators = navbar.getOperators();
			savedInstanceState.putSerializable(Globals.getOperatorsKey(), operators);
			if(tmpStop != null){
				savedInstanceState.putSerializable(Globals.getStopKey(), tmpStop);
			}
		}
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Stop tmp = (Stop) savedInstanceState.getSerializable(Globals.getStopKey());
		Operator[] operators =  (Operator[]) savedInstanceState.getSerializable(Globals.getOperatorsKey());
		if(navbar !=null){
			navbar.resetOperators(operators);
		}
		if(tmp != null){
			rtpiController.changeStop(tmp);
		}
		
	}
	
}
