package com.example.dublintravel;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

public class RtpiController {
	
	private StopInfoTable stopInfoTable;
	private Context context;
	private String currentStop;
	private Operator operator;
	private ImageView activeImageView;
	private final int DARK_GREY = Color.argb(255, 51, 51, 51);
	private final int ORANGE = Color.argb(255, 255, 153, 0);
	
	RtpiController(Context context, StopInfoTable stopInfoTable){
		this.context = context;
		currentStop="";
		this.stopInfoTable =  stopInfoTable;
	}
	
	public void changeStop(String newStop){
		currentStop = newStop;
		GetStopInfoThread si = new GetStopInfoThread(operator, currentStop);
		si.execute(stopInfoTable);
	}
	
	public void changeOperator(Operator operator, ImageView imageView){
		stopInfoTable.wipeTable();
		this.operator = operator;
		changeImageViewBorder(imageView);
	}
	
	private void changeImageViewBorder(ImageView imageView){
		int color;
		if(activeImageView != null){
			color = DARK_GREY;
			activeImageView.setBackgroundColor(color);
		}
		color = ORANGE;
		imageView.setBackgroundColor(color);
		activeImageView = imageView;
	}
	
	public Operator getCurrentOperator(){
		return operator;
	}
	
}
