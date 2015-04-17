package com.example.dublintravel;

import android.content.Context;
import android.widget.ImageView;

public abstract class Controller {
	
	protected Context context;
	protected ImageView activeImageView;
	private final double PERCENTAGE_WIDTH=1.00;
	private final int TWITTER_FEED_LANDSCAPE_PADDING=100;
	
	protected Controller(Context context){
		this.context = context;
	}
	
	protected synchronized Context getCurrentContext(){
		return context;
	}
	
	protected void changeImageViewBorder(ImageView imageView){
		if(activeImageView != null){
			activeImageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_dark_grey));
		}
		imageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_orange));
		activeImageView = imageView;
	}
	
	public abstract void changeActiveOperator(Operator operator, ImageView imageView);
	
	public double getHorizonalScrollViewPercentageWidth(){
		return PERCENTAGE_WIDTH;
	}
	
	public int getTwitterFeedLandscapePadding(){
		return TWITTER_FEED_LANDSCAPE_PADDING;
	}
}
