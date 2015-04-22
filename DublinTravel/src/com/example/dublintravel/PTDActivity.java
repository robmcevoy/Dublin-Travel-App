package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class PTDActivity extends Activity implements SmallScreenConfigurable {

	PTDController rtpiController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rtpi_dashboard);
        rtpiController = new PTDController(this);
        final Bundle EXTRAS = getIntent().getExtras();
        rtpiController.getNavBar().handleBundle(EXTRAS);
        configureSmallScreen(); 
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putAll(rtpiController.getNavBar().createBundle());
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		rtpiController.getNavBar().handleBundle(savedInstanceState);
		
	}
	
	@Override
	public void onBackPressed() {
		rtpiController.getNavBar().onBackPressed();
	}

	public void configureSmallScreen() {
		try{
			LinearLayout twitterLayout = (LinearLayout) findViewById(R.id.twitterLayout);
			LinearLayout chartVisLayout = (LinearLayout) findViewById(R.id.chartVisLayout);
			LinearLayout table = (LinearLayout) findViewById(R.id.table);
			rtpiController.configureHorizontalScrollView(this, table, chartVisLayout, twitterLayout);
		}
		catch(Exception e){/* normal layout*/};
		
	}
	
	public PTDController getController(){
		return this.rtpiController;
	}
}
