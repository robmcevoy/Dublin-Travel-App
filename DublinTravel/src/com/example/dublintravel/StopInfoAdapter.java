package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StopInfoAdapter extends ArrayAdapter<StopInfo> {

	private ArrayList<StopInfo> stopInfos;
	private Context context;
	
	public StopInfoAdapter(Context context, int textViewResourceId, ArrayList<StopInfo> stopInfos) {
		super(context, textViewResourceId, stopInfos);
		this.stopInfos = stopInfos;
		this.context = context;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.stop_info_list_item, null);
		}
		StopInfo stopInfo = stopInfos.get(position);
		if (stopInfo != null) {
			TextView stopRoute = (TextView) v.findViewById(R.id.stopInfoRoute);
			TextView destination = (TextView) v.findViewById(R.id.stopInfoDestination);
			TextView duetime = (TextView) v.findViewById(R.id.stopInfoDueTime);
			
			if(stopRoute != null){
				stopRoute.setText(stopInfo.getRouteId());
			}
			if(destination != null){
				destination.setText(stopInfo.getDestination());
			}
			if(duetime != null){
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
