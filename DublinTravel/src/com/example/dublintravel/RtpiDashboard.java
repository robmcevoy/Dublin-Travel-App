package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RtpiDashboard extends Activity {

	RtpiController rtpiController;
	NavigationBar navbar;
	private final String STOP_KEY="stop";
	private final String OPERATOR_KEY="operator";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rtpi_dashboard);
		
        final Context context = this;
        final TextView stopTextView = (TextView) findViewById(R.id.stop);
        final ListView stopInfoListView = (ListView) findViewById(R.id.stopInfoListView);
        final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
        final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
        final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
        final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
        WebView chartVis = (WebView) findViewById(R.id.webView1);
        WebView twitterFeed = (WebView) findViewById(R.id.twitterFeed);
        navbar = new NavigationBar(dublinBusImageView,luasImageView, 
		irishRailImageView,busEireannImageView);
        rtpiController = new RtpiController(context, navbar, stopTextView, stopInfoListView, chartVis, twitterFeed);
	    
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.getOpFromHomepage(EXTRAS);
        
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
			Operator tmpOperator = rtpiController.getCurrentOperator();
			savedInstanceState.putSerializable(OPERATOR_KEY, tmpOperator);
			if(tmpStop != null){
				savedInstanceState.putSerializable(STOP_KEY, tmpStop);
			}
			
		}
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Operator tmpOperator = (Operator) savedInstanceState.getSerializable(OPERATOR_KEY);
		Stop tmp = (Stop) savedInstanceState.getSerializable(STOP_KEY);
		if((tmpOperator != null) && (navbar !=null)){
			navbar.onOperatorChange(tmpOperator);
		}
		if(tmp != null){
			rtpiController.changeStop(tmp);
		}
	}

}
