package com.example.dublintravel;

import java.util.ArrayList;

import android.widget.TextView;

public class StopInfoTable {
	
	private ArrayList<TextView> table = new ArrayList<TextView>();
	
	StopInfoTable(	TextView routeId1, TextView routeId2, TextView routeId3,
					TextView dest1, TextView dest2, TextView dest3,
					TextView dueTime1, TextView dueTime2, TextView dueTime3 ){
		table.add(routeId1);
		table.add(dest1);
		table.add(dueTime1);
		table.add(routeId2);
		table.add(dest2);
		table.add(dueTime2);
		table.add(routeId3);
		table.add(dest3);
		table.add(dueTime3);
	}
	
	public TextView getTableElement(int index){
		return table.get(index);
	}
	
	public int getTableSize(){
		return table.size();
	}
	
}
