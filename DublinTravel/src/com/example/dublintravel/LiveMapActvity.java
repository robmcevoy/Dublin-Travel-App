package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class LiveMapActvity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_map);
		WebView mapview = (WebView) findViewById(R.id.map);
		LiveMap livemap = new LiveMap(mapview, this);
		livemap.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.live_map, menu);
		return true;
	}

}
