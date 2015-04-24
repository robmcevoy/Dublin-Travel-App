package com.example.dublintravel;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

/* asynchronous task that sends a http request to get real time data for a given stop
 * when the task gets the response it updates the table of stop information that appears
 * on a public transport dashboard
 */

public class GetStopInfoThread extends AsyncTask<ListView, Void, String>{

	private HttpSender hs;
	private Operator operator;
	private String stop;
	private ListView listview;
	private PTDController controller;
	private ChartWebView chartVis;
	
	public GetStopInfoThread(Operator operator, String stop, PTDController controller, ChartWebView chartVis){
		hs = new HttpSender();
		this.operator = operator;
		this.stop = stop;
		this.controller = controller;
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
			StopInfoAdapter stopInfoAdapter = new StopInfoAdapter(controller, android.R.layout.simple_list_item_1,stopInfoArray );
			listview.setAdapter(stopInfoAdapter);
			listview.setSelectionFromTop(lastViewedPosition, topOffset);
			chartVis.reload();
		}
		if(isCancelled()){
			listview.setAdapter(null);
		}
	}
	
	 protected void onCancelled(){
		 if(listview != null){
			 listview.setAdapter(null);
		 }
	 }
	
}
