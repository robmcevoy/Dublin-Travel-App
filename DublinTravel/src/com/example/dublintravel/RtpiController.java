package com.example.dublintravel;

import com.example.dublintravel.R;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

public class RtpiController {
	
	private Context context;
	private Stop currentStop;
	private Operator operator;
	private ImageView activeImageView;
	private TextView stopView;
	private StopInfoAdapter stopInfoAdapter;

	RtpiController(Context context, TextView stopView, StopInfoAdapter stopInfoAdapter){
		this.context = context;
		this.stopView = stopView;
		this.stopInfoAdapter = stopInfoAdapter;
	}
	
	public void changeStop(Stop newStop){
		currentStop = newStop;
		setStopView();
		GetStopInfoThread si = new GetStopInfoThread(operator, currentStop.getID());
		si.execute(stopInfoAdapter);
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
		stopInfoAdapter.clear();
	}
	
	private void setStopView(){
		stopView.setText(currentStop.toString());
	}
	
}
