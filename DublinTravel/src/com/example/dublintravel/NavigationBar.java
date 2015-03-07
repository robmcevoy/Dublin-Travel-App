package com.example.dublintravel;


import android.content.Context;
import android.content.Intent;
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
	
	public abstract Context getContext();

	public void resetOperators(Operator[] newOperators){
		operators = newOperators;
		setOperatorClicks();
	}
	
	public Operator[] getOperators(){
		return operators;
	}
	
	public int getNumOperators(){
		return NUM_OPERATORS;
	}
	
	public void handleBundle(Bundle extras) {
		if(extras != null){
			Object[] objects = (Object[]) extras.getSerializable(BundleKeys.getOperatorsKey());
			Operator[] newOperators = new Operator[NUM_OPERATORS];
			for(int i=0; i<objects.length; i++){
				newOperators[i] = (Operator) objects[i];
			}
			resetOperators(newOperators);
		}
	}
	
	public Bundle createBundle(){
		Bundle bundle = new Bundle();  
    	bundle.putSerializable(BundleKeys.getOperatorsKey(), operators);
    	return bundle;
	}
	
	public void onBackPressed() {
		Intent i = new Intent(getContext(), HomepageActivity.class);
		i.putExtras(createBundle());
		getContext().startActivity(i);	
	}
	
}
