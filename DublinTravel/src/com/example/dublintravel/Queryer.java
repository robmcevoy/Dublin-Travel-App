package com.example.dublintravel;

import android.widget.ListView;

public class Queryer extends Thread {
	
	private RtpiController rtpiController;
	private final int queryRate = 5000;
	
	public Queryer (RtpiController rtpiController){
		this.rtpiController = rtpiController;
	}
	
	public void run(){
		while(true){			
			GetStopInfoThread si = null;
			Operator operator = rtpiController.getCurrentOperator();
			Stop stop = rtpiController.getCurrentStop();
			ChartWebView chartVis = rtpiController.getChartWebView();
			ListView stopInfoListView = rtpiController.getStopInfoListView();
			if(stop != null){
				si = new GetStopInfoThread(operator, stop.getID(), rtpiController, chartVis);
				si.execute(stopInfoListView);
			}
			try {
				Thread.sleep(queryRate);
			} catch (InterruptedException e) {
				if(si != null){
					si.cancel(true);
				}
			}
		}
	}
	

}
