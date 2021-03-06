package com.example.dublintravel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/* an abstract class that controls navigation between public transport operators and activities
 * classes inheriting this class must implement operatorClick & setMapClick because depending on
 * the activity the reaction to these clicks will be different
 */

public abstract class NavigationBar {
		
	protected final static int NUM_OPERATORS=4;
	protected final static String OPERATORS_KEY = "operators";
	protected Operator[] operators;
	protected ImageView[] imageviews;
    protected ImageView mapImageView;
    protected Activity activity;

    
    public NavigationBar(Activity activity, int layout_id){
    	activity.setTheme(Theme.getCurrentTheme());
		activity.setContentView(layout_id);
    	this.activity = activity;
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
    	imageviews[dublinBusOperator.getIndex()] =(ImageView) activity.findViewById(R.id.dublinBusLogo);
		imageviews[luasOperator.getIndex()] = (ImageView) activity.findViewById(R.id.luasLogo);
		imageviews[irishRailOperator.getIndex()] = (ImageView) activity.findViewById(R.id.irishRailLogo);
		imageviews[busEireannOperator.getIndex()] =  (ImageView) activity.findViewById(R.id.busEireannLogo);
		this.mapImageView = (ImageView) activity.findViewById(R.id.liveMapLogo);
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
	
	public Context getContext(){
		return this.activity.getApplicationContext();
	}

	public void resetOperators(Operator[] newOperators){
		operators = newOperators;
		setOperatorClicks();
	}
	
	public Operator[] getOperators(){
		return operators;
	}
	
	public static int getNumOperators(){
		return NUM_OPERATORS;
	}
	
	public void handleBundle(Bundle extras) {
		if(extras != null){
			Object[] objects = (Object[]) extras.getSerializable(OPERATORS_KEY);
			Operator[] newOperators = new Operator[NUM_OPERATORS];
			for(int i=0; i<objects.length; i++){
				newOperators[i] = (Operator) objects[i];
			}
			resetOperators(newOperators);
		}
	}
	
	public Bundle createBundle(){
		Bundle bundle = new Bundle();  
    	bundle.putSerializable(OPERATORS_KEY, operators);
    	return bundle;
	}
	
	public void onBackPressed() {
		Intent i = new Intent(getContext(), HomepageActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtras(createBundle());
		getContext().startActivity(i);	
	}
	
	public static String getOperatorsKey(){
		return OPERATORS_KEY;
	}
	
}
