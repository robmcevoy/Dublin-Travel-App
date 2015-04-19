package com.example.dublintravel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
	
	public void configureHorizontalScrollView(final Activity activity, final View view1, final View view2, final View twitter) throws Exception{
			Display display = activity.getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = (int)(size.x * getHorizonalScrollViewPercentageWidth());
			view1.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			view2.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			twitter.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.MATCH_PARENT));
			int orientation = activity.getResources().getConfiguration().orientation;
			if(orientation == 2){
				twitter.setPadding(getTwitterFeedLandscapePadding(), twitter.getPaddingTop(), getTwitterFeedLandscapePadding(), twitter.getPaddingBottom());
			}
			final HorizontalScrollView scrollview = (HorizontalScrollView)activity. findViewById(R.id.scrollview);
			scrollview.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener(){

				@Override
				public void onScrollChanged() {
					UpdateScrollViewDotsThread thread = new UpdateScrollViewDotsThread(activity, view1, view2, twitter);
			    	Rect scrollBounds = new Rect();
			    	scrollview.getHitRect(scrollBounds);
			    	thread.execute(scrollBounds);
				}
			});
	}
}
