package com.example.dublintravel;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
		/*
		final ListView listview1 = (ListView) findViewById(R.id.stopInfoView);
		final ListView listview2 = (ListView) findViewById(R.id.twitter);
		final ImageView irishRailLogo = (ImageView) findViewById(R.id.irishRailLogo);
		irishRailLogo.setClickable(true);
		irishRailLogo.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {  
		    }
		});
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile", "adbab", "anything", "anything", "anything"};

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
	    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    listview1.setAdapter(arrayAdapter);
	    listview2.setAdapter(arrayAdapter);
	    */
	    /***************************************************************/
	    
	    final EditText stop = (EditText) findViewById(R.id.stop); 
	    final Button stopEntered = (Button) findViewById(R.id.goBtn);
	    final TextView busId1 = (TextView) findViewById(R.id.busId1);
	    final TextView busId2 = (TextView) findViewById(R.id.busId2);
	    final TextView busId3 = (TextView) findViewById(R.id.busId3);
	    final TextView dest1 = (TextView) findViewById(R.id.destination1);
	    final TextView dest2 = (TextView) findViewById(R.id.destination2);
	    final TextView dest3 = (TextView) findViewById(R.id.destination3);
	    final TextView dueTime1 = (TextView) findViewById(R.id.dueTime1);
	    final TextView dueTime2 = (TextView) findViewById(R.id.dueTime2);
	    final TextView dueTime3 = (TextView) findViewById(R.id.dueTime3);
	    final StopInfoTable stopInfoTable = new StopInfoTable(busId1, busId2, busId3,
	    												dest1, dest2, dest3,
	    												dueTime1, dueTime2, dueTime3);
	    final Context context = this;
	    
	    stopEntered.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	GetThread si = new GetThread(context, stop.getText().toString());
    			si.execute(stopInfoTable);
            }
        });
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
