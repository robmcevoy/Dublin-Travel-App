package com.example.dublintravel;

import android.os.AsyncTask;
import android.widget.ListView;

public class Queryer extends Thread {
	
	private PTDController controller;
	private final int queryRate = 5000;
	
	public Queryer (PTDController controller){
		this.controller = controller;
	}
	
	public void run(){
		while(true){			
			AsyncTask<ListView, Void, String> executing = null;
			GetStopInfoThread si = null;
			Operator operator = controller.getCurrentOperator();
			Stop stop = controller.getCurrentStop();
			ChartWebView chartVis = controller.getChartWebView();
			ListView stopInfoListView = controller.getStopInfoListView();
			if(stop != null){
				si = new GetStopInfoThread(operator, stop.getID(), controller, chartVis);
				executing = si.execute(stopInfoListView);
			}
			try {
				Thread.sleep(queryRate);
			} catch (InterruptedException e) {
				if(executing !=null){
					executing.cancel(true);
				}
			}
		}
	}
}