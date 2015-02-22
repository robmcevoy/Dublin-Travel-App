package com.example.dublintravel;

import android.content.Context;
import android.widget.ListView;

public class Queryer extends Thread {
	
	private RtpiController rtpiController;
	private final int queryRate = 5000;
	private Context context;
	
	public Queryer (RtpiController rtpiController, Context context){
		this.rtpiController = rtpiController;
		this.context = context;
	}
	
	public void run(){
		while(true){
			Operator operator = rtpiController.getCurrentOperator();
			Stop stop = rtpiController.getCurrentStop();
			ChartWebView chartVis = rtpiController.getChartWebView();
			ListView stopInfoListView = rtpiController.getStopInfoListView();
			if(stop != null){
				GetStopInfoThread si = new GetStopInfoThread(operator, stop.getID(), context, chartVis);
				si.execute(stopInfoListView);
			}
			try {
				Thread.sleep(queryRate);
			} catch (InterruptedException e) {
			}
		}
	}
	

}
