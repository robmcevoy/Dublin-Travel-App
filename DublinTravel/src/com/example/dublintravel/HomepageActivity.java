package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class HomepageActivity extends Activity {
	
	private final String OPERATOR = "active_operator";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
	    final DublinBusOperator dublinBusOperator = new DublinBusOperator();
	    final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
	    final IrishRailOperator irishRailOperator = new IrishRailOperator();
	    final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
	    final BusEireannOperator busEireannOperator = new BusEireannOperator();
	    final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
	    final LuasOperator luasOperator = new LuasOperator();
	    final ImageView liveMapLogoImageView = (ImageView) findViewById(R.id.liveMapLogo);
	    

	    launchDashboardClick(dublinBusImageView, dublinBusOperator);
	    launchDashboardClick(luasImageView, luasOperator);
	    launchDashboardClick(irishRailImageView, irishRailOperator);
	    launchDashboardClick( busEireannImageView, busEireannOperator);
	    launchLiveMapClick(liveMapLogoImageView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.homepage, menu);
		return true;
	}
	
	public void launchDashboardClick(ImageView imageView, final Operator operator){
		imageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getApplicationContext(), RtpiDashboardActivity.class);
            	i.putExtra(OPERATOR, operator);
            	startActivity(i);
            }
        });
	}
	
	public void launchLiveMapClick(ImageView imageView){
		imageView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getApplicationContext(), LiveMapActvity.class);
            	startActivity(i);
            }
        });
	}
}
