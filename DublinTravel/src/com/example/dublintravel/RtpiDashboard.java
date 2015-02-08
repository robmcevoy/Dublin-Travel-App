package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RtpiDashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rtpi_dashboard);
		
		/****************** TEMP STUFF *******************/
		WebView myWebView = (WebView) findViewById(R.id.webView1);
		myWebView.loadUrl("http://www.google.ie");
		myWebView.setWebViewClient(new WebViewClient() {
	        @Override
	        public void onReceivedError(WebView view, int errorCode,
	                String description, String failingUrl) {
	            // Handle the error
	        }

	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }});
	    /***************************************************************/
	    
	    final EditText stop = (EditText) findViewById(R.id.stop);
	    final Button stopEntered = (Button) findViewById(R.id.goBtn);
	    final TextView routeId1 = (TextView) findViewById(R.id.routeId1);
	    final TextView routeId2 = (TextView) findViewById(R.id.routeId2);
	    final TextView routeId3 = (TextView) findViewById(R.id.routeId3);
	    final TextView routeId4 = (TextView) findViewById(R.id.routeId4);
	    final TextView routeId5 = (TextView) findViewById(R.id.routeId5);
	    final TextView dest1 = (TextView) findViewById(R.id.destination1);
	    final TextView dest2 = (TextView) findViewById(R.id.destination2);
	    final TextView dest3 = (TextView) findViewById(R.id.destination3);
	    final TextView dest4 = (TextView) findViewById(R.id.destination4);
	    final TextView dest5 = (TextView) findViewById(R.id.destination5);
	    final TextView dueTime1 = (TextView) findViewById(R.id.dueTime1);
	    final TextView dueTime2 = (TextView) findViewById(R.id.dueTime2);
	    final TextView dueTime3 = (TextView) findViewById(R.id.dueTime3);
	    final TextView dueTime4 = (TextView) findViewById(R.id.dueTime4);
	    final TextView dueTime5 = (TextView) findViewById(R.id.dueTime5);
	    final StopInfoTable stopInfoTable = new StopInfoTable(routeId1, routeId2, routeId3, routeId4, routeId5,
	    													dest1, dest2, dest3, dest4, dest5,
	    													dueTime1, dueTime2, dueTime3, dueTime4, dueTime5);
	    final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
	    final DublinBusOperator dublinBusOperator = new DublinBusOperator();
	    final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
	    final RtpiXmlOperator irishRailOperator = new IrishRailOperator();
	    final BusEireannOperator busEireannOperator = new BusEireannOperator();
	    final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
	    final LuasOperator luasOperator = new LuasOperator();
	    final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
	    
	    final RtpiController rtpiController = new RtpiController(this,stopInfoTable);
	    
	    final Bundle EXTRAS = getIntent().getExtras();
		String  selected;
		if (EXTRAS != null) {
			selected = (String) EXTRAS.get("operator");
			if(selected.equals(dublinBusOperator.getOperatorCode()))
				rtpiController.changeOperator(dublinBusOperator, dublinBusImageView);
			else if(selected.equals(luasOperator.getOperatorCode()))
				rtpiController.changeOperator(luasOperator, luasImageView);
			else if(selected.equals(busEireannOperator.getOperatorCode()))
				rtpiController.changeOperator(busEireannOperator, busEireannImageView);
			else
				rtpiController.changeOperator(irishRailOperator, irishRailImageView);
		}
	    
	    stopEntered.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	String newStop = stop.getText().toString();
            	if(!newStop.equals("")){
            		rtpiController.changeStop(newStop);
            	}
            }
        });
	    
	    operatorClick(irishRailImageView,irishRailOperator, rtpiController );
	    operatorClick(luasImageView,luasOperator, rtpiController );
	    operatorClick(busEireannImageView,busEireannOperator, rtpiController );
	    operatorClick(dublinBusImageView,dublinBusOperator, rtpiController );
	    
	    
	}
	
	public void operatorClick(final ImageView imageview, final Operator operator, final RtpiController rtpiController ){
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	rtpiController.changeOperator(operator, imageview);
            }
        });
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
