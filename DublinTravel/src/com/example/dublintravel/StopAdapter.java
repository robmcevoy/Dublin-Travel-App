package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StopAdapter extends ArrayAdapter<Stop> {
	
	private ArrayList<Stop> stops;
	private PTDController controller;
	
	public StopAdapter(PTDController controller, int textViewResourceId, ArrayList<Stop> stops) {
		super(controller.getCurrentContext(), textViewResourceId, stops);
		this.stops = stops;
		this.controller = controller;
	}

	public ArrayList<Stop> getStops(){
		return this.stops;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)controller.getCurrentContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.stop_list_item, null, false);
		}
		Stop stop = stops.get(position);
		if (stop != null) {
			TextView stopName = (TextView) v.findViewById(R.id.stopNameTextView);
			TextView stopId = (TextView) v.findViewById(R.id.stopIdTextView);
			ImageView favourite = (ImageView) v.findViewById(R.id.favourite);
			if (stopName != null) {
				stopName.setText(stop.getName().trim());
			}
			if(stopId != null) {
				stopId.setText(stop.getID().trim());
			}
			if(stop.isFavourite()){
				favourite.setImageResource(R.drawable.ic_action_important_active);
			}
			else{
				favourite.setImageResource(R.drawable.ic_action_important);
			}	
		}
		return v;
	}

}
