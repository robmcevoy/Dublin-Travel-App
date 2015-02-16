package com.example.dublintravel;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RtpiController {
	
	private Context context;
	private Stop currentStop;
	private Operator operator;
	private ImageView activeImageView;
	private TextView stopView;
	private ListView stopInfoListView;
	
	RtpiController(Context context, TextView stopView, ListView stopInfoListView){		
		this.context = context;
		this.stopView = stopView;
		this.stopInfoListView = stopInfoListView;
	}
	
	public void changeStop(Stop newStop){
		currentStop = newStop;
		setStopView();
		GetStopInfoThread si = new GetStopInfoThread(operator, currentStop.getID(), context);
		si.execute(stopInfoListView);
	}
	
	public void changeOperator(Operator operator, ImageView imageView){
		wipeStopView();
		wipeStopInfoView();
		this.operator = operator;
		changeImageViewBorder(imageView);
	}
	
	private void changeImageViewBorder(ImageView imageView){
		if(activeImageView != null){
			activeImageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_dark_grey));
		}
		imageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_orange));
		activeImageView = imageView;
	}
	
	public Operator getCurrentOperator(){
		return operator;
	}
	
	private void wipeStopView(){
		stopView.setText(context.getResources().getString(R.string.stop_id_hint));
	}
	
	private void wipeStopInfoView(){
		stopInfoListView.setAdapter(null);
	}
	
	private void setStopView(){
		stopView.setText(currentStop.toString());
	}
	
}
