package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RtpiDashboard extends Activity {

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
	    final RtpiController rtpiController = new RtpiController(this,stopTextView, stopInfoListView, chartVis );
	    NavigationBar navbar = new NavigationBar(dublinBusImageView,luasImageView, 
												irishRailImageView,busEireannImageView,
												rtpiController);
	    navbar.activate();
	    
	    final Bundle EXTRAS = getIntent().getExtras();
	    navbar.getOpFromHomepage(EXTRAS);

		
		stopTextView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	StopListDialog stopListDialog = new StopListDialog(context, rtpiController);
            	stopListDialog.open();
            }
        });
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
