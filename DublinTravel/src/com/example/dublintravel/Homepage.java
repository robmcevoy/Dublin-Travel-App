package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class Homepage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
	    final DublinBusOperator dublinBusOperator = new DublinBusOperator();
	    final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
	    final RtpiXmlOperator irishRailOperator = new RtpiXmlOperator();
	    final BusEireannOperator busEireannOperator = new BusEireannOperator();
	    final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
	    final LuasOperator luasOperator = new LuasOperator();
	    final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
	    final ImageView carsImageView = (ImageView) findViewById(R.id.carsLogo);
	    
	    dublinBusImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getApplicationContext(), RtpiDashboard.class);
            	startActivity(i);
            }
        });
	    
	    luasImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getApplicationContext(), RtpiDashboard.class);
            	startActivity(i);
            }
        });
	    
	    irishRailImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {	
            	Intent i = new Intent(getApplicationContext(), RtpiDashboard.class);
            	startActivity(i);
            }
        });
	    
	    busEireannImageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getApplicationContext(), RtpiDashboard.class);
            	startActivity(i);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.homepage, menu);
		return true;
	}

}
