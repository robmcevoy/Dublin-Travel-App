package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetStopInfoThread extends AsyncTask<ListView, Void, String>{

	private HttpSender hs;
	private Operator operator;
	private String stop;
	private ListView listview;
	private Context context;
	private ChartWebView chartVis;
	
	public GetStopInfoThread(Operator operator, String stop, Context context, ChartWebView chartVis){
		hs = new HttpSender();
		this.operator = operator;
		this.stop =stop;
		this.context = context;
		this.chartVis = chartVis;
	}
	
	protected String doInBackground(ListView... arg0) {
		listview = arg0[0];
		return hs.sendGetRequest(operator.generateRealtimeInfoUrlString(stop), operator.needsAuth());
	}

	protected void onPostExecute(String result) {

		ArrayList<StopInfo> stopInfoArray = operator.getParser().getStopInfo(result);
		StopInfoAdapter stopInfoAdapter = new StopInfoAdapter(context, android.R.layout.simple_list_item_1,stopInfoArray );
		listview.setAdapter(stopInfoAdapter);
		chartVis.reload();
	}
	
}
