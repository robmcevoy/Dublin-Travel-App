package com.example.dublintravel;

import java.util.ArrayList;

import com.example.dublintravel.R;

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
	    WebView chartVis = (WebView) findViewById(R.id.webView1);
		ChartWebView webview = new ChartWebView(chartVis, stopInfoAdapter, rtpiController);
		webview.start();
	    
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
