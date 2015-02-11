package com.example.dublintravel;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

public class RtpiController {
	
	private StopInfoTable stopInfoTable;
	private Context context;
	private Stop currentStop;
	private Operator operator;
	private ImageView activeImageView;
	private final int DARK_GREY = Color.argb(255, 51, 51, 51);
	private final int ORANGE = Color.argb(255, 255, 153, 0);
	private TextView stopView;
	
	RtpiController(Context context, StopInfoTable stopInfoTable, TextView stopView){
		this.context = context;
		this.stopInfoTable =  stopInfoTable;
		this.stopView = stopView;
	}
	
	public void changeStop(Stop newStop){
		currentStop = newStop;
		setStopView();
		GetStopInfoThread si = new GetStopInfoThread(operator, currentStop.getID());
		si.execute(stopInfoTable);
	}
	
	public void changeOperator(Operator operator, ImageView imageView){
		wipeStopView();
		stopInfoTable.wipeTable();
		this.operator = operator;
		changeImageViewBorder(imageView);
	}
	
	private void changeImageViewBorder(ImageView imageView){
		//int color;
		if(activeImageView != null){
			//color = DARK_GREY;
			//activeImageView.setBackgroundColor(color);
			activeImageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_dark_grey));
		}
		//color = ORANGE;
		//imageView.setBackgroundColor(color);
		imageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_orange));
		activeImageView = imageView;
	}
	
	public Operator getCurrentOperator(){
		return operator;
	}
	
	private void wipeStopView(){
		stopView.setText(context.getResources().getString(R.string.stop_id_hint));
	}
	
	private void setStopView(){
		stopView.setText(currentStop.toString());
	}
	
}
