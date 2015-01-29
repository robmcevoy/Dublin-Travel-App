package com.example.dublintravel;

import java.util.ArrayList;

import android.widget.TextView;

public class StopInfoTable {
	
	ArrayList<TextView> table = new ArrayList<TextView>();
	
	StopInfoTable(	TextView busId1, TextView busId2, TextView busId3,
					TextView dest1, TextView dest2, TextView dest3,
					TextView dueTime1, TextView dueTime2, TextView dueTime3 ){
		table.add(busId1);
		table.add(busId2);
		table.add(busId3);
		table.add(dest1);
		table.add(dest2);
		table.add(dest3);
		table.add(dueTime1);
		table.add(dueTime2);
		table.add(dueTime3);
	}
	
	TextView getTableElement(int index){
		return table.get(index);
	}
	
}
