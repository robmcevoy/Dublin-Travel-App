package com.example.dublintravel;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

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
	    final Context context = this;
	    final TextView stopTextView = (TextView) findViewById(R.id.stop);
	    ArrayList<StopInfo> stopInfoArray = new ArrayList<StopInfo>();
	    StopInfoAdapter stopInfoAdapter = new StopInfoAdapter(this, android.R.layout.simple_list_item_1,stopInfoArray );
	    final ListView stopInfoListView = (ListView) findViewById(R.id.stopInfoListView);
	    stopInfoListView.setAdapter(stopInfoAdapter);
	    final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
	    final DublinBusOperator dublinBusOperator = new DublinBusOperator();
	    final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
	    final RtpiXmlOperator irishRailOperator = new IrishRailOperator();
	    final BusEireannOperator busEireannOperator = new BusEireannOperator();
	    final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
	    final LuasOperator luasOperator = new LuasOperator();
	    final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
	    final RtpiController rtpiController = new RtpiController(this,stopTextView, stopInfoAdapter);
	    
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
		

		
		stopTextView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	StopListDialog stopListDialog = new StopListDialog(context, rtpiController);
            	stopListDialog.open();
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
