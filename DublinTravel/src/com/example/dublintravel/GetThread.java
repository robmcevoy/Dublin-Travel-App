package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GetThread extends AsyncTask<StopInfoTable, Void, String>{

	StopInfoTable stopInfoTable;
	Context context;
	HttpSender hs;
	JSONHelper jh;
	String stop;
	
	public GetThread(Context context, String stop){
		this.context = context;
		hs = new HttpSender();
		jh = new JSONHelper();
		this.stop =stop;
	}
	
	protected String doInBackground(StopInfoTable... arg0) {
		//HttpSender httpSender = new HttpSender();
		stopInfoTable = arg0[0]; 
		return hs.sendGetRequest("http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid="+stop+"&operator=bac&format=json", true);
	}
	
	 protected void onPostExecute(String result) {
		 /*
		 final ArrayList<String> list = new ArrayList<String>();
		 result = jh.getJSONNextDue(result);
		 list.add(result + " mins");
		 ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, list);
		 lv.setAdapter(arrayAdapter);
		 */
		 StopInfo Object = jh.getJSONNextDue(result);
		 TextView t =stopInfoTable.getTableElement(6);
		 TextView t1 =stopInfoTable.getTableElement(0);
		 TextView t2 =stopInfoTable.getTableElement(3);
		 t1.setText(Object.getBusId());
		 t2.setText(Object.getDestination());
		 t.setText(Object.getDueTime());
     }

}
