package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HomepageActivity extends Activity {
	
	HomepageNavigationBar navbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
	    navbar = new HomepageNavigationBar(this);
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
