package com.example.dublintravel;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

public class GetStopInfoThread extends AsyncTask<ListView, Void, String>{

	private HttpSender hs;
	private Operator operator;
	private String stop;
	private ListView listview;
	private RtpiController rtpiController;
	private ChartWebView chartVis;
	
	public GetStopInfoThread(Operator operator, String stop, RtpiController rtpiController, ChartWebView chartVis){
		hs = new HttpSender();
		this.operator = operator;
		this.stop = stop;
		this.rtpiController = rtpiController;
		this.chartVis = chartVis;
	}
	
	protected String doInBackground(ListView... arg0) {
		if(!isCancelled()){
			listview = arg0[0];
			return hs.sendGetRequest(operator.generateRealtimeInfoUrlString(stop), operator.needsAuth());
		}
		else return "";
	}

	protected void onPostExecute(String result) {

		if(!isCancelled()){
			int lastViewedPosition = listview.getFirstVisiblePosition();
			View v = listview.getChildAt(0);
			int topOffset = (v == null) ? 0 : v.getTop();
		
			ArrayList<StopInfo> stopInfoArray = operator.getParser().getStopInfo(result);
			StopInfoAdapter stopInfoAdapter = new StopInfoAdapter(rtpiController, android.R.layout.simple_list_item_1,stopInfoArray );
			listview.setAdapter(stopInfoAdapter);
			listview.setSelectionFromTop(lastViewedPosition, topOffset);
			chartVis.reload();
		}
	}
	
}
