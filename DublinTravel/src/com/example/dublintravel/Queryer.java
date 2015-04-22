package com.example.dublintravel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.widget.ListView;

public class Queryer extends Thread {
	
	private PTDController rtpiController;
	private final int queryRate = 5000;
	private ExecutorService threadExecutor;
	
	public Queryer (PTDController rtpiController){
		this.rtpiController = rtpiController;
		threadExecutor= Executors.newFixedThreadPool(1);
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
				si.executeOnExecutor(threadExecutor, stopInfoListView);
			}
			try {
				Thread.sleep(queryRate);
			} catch (InterruptedException e) {
				this.threadExecutor.shutdownNow();
				threadExecutor= Executors.newFixedThreadPool(1);
			}
		}
	}
}