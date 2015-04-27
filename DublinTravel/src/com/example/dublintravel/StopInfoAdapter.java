package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StopInfoAdapter extends ArrayAdapter<StopInfo> {

	private ArrayList<StopInfo> stopInfos;
	private PTDController controller;
	
	public StopInfoAdapter(PTDController controller, int textViewResourceId, ArrayList<StopInfo> stopInfos) {
		super(controller.getCurrentContext(), textViewResourceId, stopInfos);
		this.stopInfos = stopInfos;
		this.controller = controller;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)controller.getCurrentContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.stop_info_list_item, null);
		}
		StopInfo stopInfo = stopInfos.get(position);
		if (stopInfo != null) {
			TextView stopRoute = (TextView) v.findViewById(R.id.stopInfoRoute);
			TextView destination = (TextView) v.findViewById(R.id.stopInfoDestination);
			TextView duetime = (TextView) v.findViewById(R.id.stopInfoDueTime);
			int[] attrs = {android.R.attr.textColor};
			TypedArray ta = controller.getActivity().obtainStyledAttributes(Theme.getCurrentTheme(), attrs);
			int textColor = ta.getColor(0, Color.BLACK);
			
			if(stopRoute != null){
				stopRoute.setTextColor(textColor);
				stopRoute.setText(stopInfo.getRouteId());
			}
			if(destination != null){
				destination.setTextColor(textColor);
				destination.setText(stopInfo.getDestination());
			}
			if(duetime != null){
				duetime.setTextColor(textColor);
				duetime.setText(stopInfo.getDueTime());
			}	
		}
		return v;
	}
	
	@Override
    public boolean isEnabled(int position) {
       return false;
	}

}
