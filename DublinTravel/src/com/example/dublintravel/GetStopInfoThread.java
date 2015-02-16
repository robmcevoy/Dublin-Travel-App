package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GetStopInfoThread extends AsyncTask<ListView, Void, String>{

	HttpSender hs;
	Operator operator;
	String stop;
	ListView listview;
	Context context;
	
	public GetStopInfoThread(Operator operator, String stop, Context context){
		hs = new HttpSender();
		this.operator = operator;
		this.stop =stop;
		this.context = context;
	}
	
	protected String doInBackground(ListView... arg0) {
		listview = arg0[0];
		return hs.sendGetRequest(operator.generateRealtimeInfoUrlString(stop), operator.needsAuth());
	}

	protected void onPostExecute(String result) {

		ArrayList<StopInfo> stopInfoArray = operator.getParser().getStopInfo(result);
		StopInfoAdapter stopInfoAdapter = new StopInfoAdapter(context, android.R.layout.simple_list_item_1,stopInfoArray );
		listview.setAdapter(stopInfoAdapter);
	}
	
}
