package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;

public class LiveMapActivity extends Activity {
	
	LiveMapController controller;
	LiveMapNavigationBar navbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_map);
		final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
        final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
        final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
        final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
        final ImageView liveMapImageView = (ImageView) findViewById(R.id.liveMapLogo);
        navbar = new LiveMapNavigationBar(dublinBusImageView,luasImageView, 
		irishRailImageView,busEireannImageView, liveMapImageView);
        controller = new LiveMapController(this, navbar);
		WebView mapview = (WebView) findViewById(R.id.map);
		LiveMap livemap = new LiveMap(mapview, this);
		livemap.start();
		
		// handle bundle
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.handleBundle(EXTRAS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.live_map, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		navbar.onBackPressed();
	}

}
