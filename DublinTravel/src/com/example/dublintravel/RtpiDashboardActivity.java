package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	    
        setSmallScreenConfig();
        // handle bundle
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.handleBundle(EXTRAS);   
	}
	
	private void setSmallScreenConfig(){
		try{
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = (int)(size.x * rtpiController.getHorizonalScrollViewPercentageWidth());
			LinearLayout twitterLayout = (LinearLayout) findViewById(R.id.twitterLayout);
			LinearLayout chartVisLayout = (LinearLayout) findViewById(R.id.chartVisLayout);
			LinearLayout table = (LinearLayout) findViewById(R.id.table);
			twitterLayout.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			chartVisLayout.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			table.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			int orientation = getResources().getConfiguration().orientation;
			if(orientation == 2){
				twitterLayout.setPadding(rtpiController.getTwitterFeedLandscapePadding(), twitterLayout.getPaddingTop(), rtpiController.getTwitterFeedLandscapePadding(), twitterLayout.getPaddingBottom());
			}
		}
		catch(Exception e){
			// use large layout
		}
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putAll(navbar.createBundle());
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		navbar.handleBundle(savedInstanceState);
		
	}
	
	@Override
	public void onBackPressed() {
		navbar.onBackPressed();
	}
	
}
