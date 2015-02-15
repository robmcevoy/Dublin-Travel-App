package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StopAdapter extends ArrayAdapter<Stop> {
	
	private ArrayList<Stop> stops;
	private Context context;
	
	public StopAdapter(Context context, int textViewResourceId, ArrayList<Stop> stops) {
		super(context, textViewResourceId, stops);
		this.stops = stops;
		this.context = context;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.stop_list_item, null);
		}
		Stop stop = stops.get(position);
		if (stop != null) {
			TextView stopName = (TextView) v.findViewById(R.id.stopNameTextView);
			TextView stopId = (TextView) v.findViewById(R.id.stopIdTextView);
			if (stopName != null) {
				stopName.setText(stop.getName());
			}
			if(stopId != null) {
				stopId.setText(stop.getID() );
			}
		}
		return v;
	}

}
