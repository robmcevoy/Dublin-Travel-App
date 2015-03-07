package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class HomepageActivity extends Activity {
	
	HomepageNavigationBar navbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
	    final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
	    final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
	    final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
	    final ImageView liveMapLogoImageView = (ImageView) findViewById(R.id.liveMapLogo);
	    
	    navbar = new HomepageNavigationBar(dublinBusImageView, luasImageView, irishRailImageView, busEireannImageView, liveMapLogoImageView, this);
	    navbar.activate();
	    
	    // handle bundle
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.handleBundle(EXTRAS);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.homepage, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		navbar.onBackPressed();
	}
}
