package com.example.dublintravel;


import android.os.Bundle;
import android.widget.ImageView;

public abstract class NavigationBar {
		
	protected final int NUM_OPERATORS=4;
	protected Operator[] operators;
	protected ImageView[] imageviews;
    protected ImageView mapImageView;

  
	public NavigationBar(	ImageView dublinBusImageView, ImageView luasImageView, 
							ImageView irishRailImageView, ImageView busEireannImageView,
							ImageView mapImageView){
		operators = new Operator[NUM_OPERATORS];
		imageviews = new ImageView[NUM_OPERATORS];
		DublinBusOperator dublinBusOperator = new DublinBusOperator();
		operators[dublinBusOperator.getIndex()] = dublinBusOperator;
		IrishRailOperator irishRailOperator = new IrishRailOperator();
		operators[irishRailOperator.getIndex()] = irishRailOperator;
		BusEireannOperator busEireannOperator = new BusEireannOperator();
		operators[busEireannOperator.getIndex()] = busEireannOperator;
		LuasOperator luasOperator = new LuasOperator();
		operators[luasOperator.getIndex()] = luasOperator;
		imageviews[dublinBusOperator.getIndex()] = dublinBusImageView;
		imageviews[luasOperator.getIndex()] = luasImageView;
		imageviews[irishRailOperator.getIndex()] =irishRailImageView;
		imageviews[busEireannOperator.getIndex()] = busEireannImageView;
		this.mapImageView = mapImageView;
	}
	
	
	protected void activate(){
		setOperatorClicks();
		setMapClick(mapImageView);
	}
	
	public void setOperatorClicks(){
		for(int i=0; i<NUM_OPERATORS; i++){
			operatorClick(imageviews[i], operators[i]);
		}
	}
	
	public abstract void operatorClick(final ImageView imageview, final Operator operator);
	
	public abstract void setMapClick(final ImageView imageview);
	
	public abstract void handleBundle(Bundle extras);

	public void resetOperators(Operator[] newOperators){
		operators = newOperators;
		setOperatorClicks();
	}
	
	public Operator[] getOperators(){
		return operators;
	}
	
	
}
