package com.example.dublintravel;

import java.util.ArrayList;


import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GetStopInfoThread extends AsyncTask<ArrayAdapter<StopInfo>, Void, String>{

	//StopInfoTable stopInfoTable;
	HttpSender hs;
	Operator operator;
	String stop;
	ArrayAdapter<StopInfo> adapter;
	
	public GetStopInfoThread(Operator operator, String stop){
		hs = new HttpSender();
		this.operator = operator;
		this.stop =stop;
	}
	
	protected String doInBackground(ArrayAdapter<StopInfo>... arg0) {
		adapter = arg0[0];
		return hs.sendGetRequest(operator.generateRealtimeInfoUrlString(stop), operator.needsAuth());
	}
	
	protected void onPostExecute(String result) {
		adapter.clear();
		ArrayList<StopInfo> stopInfoArray = operator.getParser().getStopInfo(result);
		for(StopInfo stopInfo: stopInfoArray){
			adapter.add(stopInfo);
		}
	}
}
