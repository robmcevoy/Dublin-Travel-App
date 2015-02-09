package com.example.dublintravel;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetStopInfoThread extends AsyncTask<StopInfoTable, Void, String>{

	StopInfoTable stopInfoTable;
	HttpSender hs;
	Operator operator;
	String stop;
	
	public GetStopInfoThread(Operator operator, String stop){
		hs = new HttpSender();
		this.operator = operator;
		this.stop =stop;
	}
	
	protected String doInBackground(StopInfoTable... arg0) {
		stopInfoTable = arg0[0]; 
		return hs.sendGetRequest(operator.generateRealtimeInfoUrlString(stop), operator.needsAuth());
	}
	
	protected void onPostExecute(String result) {

		wipe();
		ArrayList<StopInfo> stopInfoArray = operator.getParser().getStopInfo(result);
		TextView route;
		TextView dest;
		TextView duetime;
		int index = 0;
		for(StopInfo stopInfo:stopInfoArray){
			route = stopInfoTable.getTableElement(index);
			dest = stopInfoTable.getTableElement(index+1);
			duetime = stopInfoTable.getTableElement(index+2);
			route.setText(stopInfo.getRouteId());
			dest.setText(stopInfo.getDestination());
			duetime.setText(stopInfo.getDueTime());
			index = index+3;
		}
    }
	
	public void wipe(){
		TextView textView;
		for(int i=0; i<stopInfoTable.getTableSize(); i++){
			textView = stopInfoTable.getTableElement(i);
			textView.setText("");
		}
	}

}
