package com.example.dublintravel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/* activity that runs the public transport dashboard */

public class PTDActivity extends Activity {

	PTDController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller = new PTDController(this);
        final Bundle EXTRAS = getIntent().getExtras();
        controller.getNavBar().handleBundle(EXTRAS);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putAll(controller.getNavBar().createBundle());
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		controller.getNavBar().handleBundle(savedInstanceState);
	}
	
	@Override
	public void onBackPressed() {
		controller.getNavBar().onBackPressed();
	}
	
	public PTDController getController(){
		return this.controller;
	}
}
