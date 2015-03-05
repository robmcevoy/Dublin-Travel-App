package com.example.dublintravel;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RtpiNavigationBar extends NavigationBar {
	
	private RtpiController controller;
	
	public RtpiNavigationBar(	ImageView dublinBusImageView,
								ImageView luasImageView, ImageView irishRailImageView,
								ImageView busEireannImageView, ImageView mapImageView) {
		super(dublinBusImageView, luasImageView, irishRailImageView,busEireannImageView, mapImageView);
	}
	
	public void activate(RtpiController controller){
		this.controller = controller;
		super.activate();
	}
	
	
	public void onOperatorChange(Operator operator){
		for(int i=0; i<NUM_OPERATORS; i++){
			if(operator.equals(operators[i])){
				controller.changeActiveOperator(operators[i], imageviews[i]);
			}
		}
	}
	
	public void resetOperators(Operator[] newOperators){
		super.resetOperators(newOperators);
		for(int i=0; i<NUM_OPERATORS; i++){
			if(operators[i].isActive()){
				onOperatorChange(operators[i]);
			}
		}
	}
	
	
	public void handleBundle(Bundle extras){
		if(extras != null){
			Object[] objects = (Object[]) extras.getSerializable(Globals.getOperatorsKey());
			Operator[] newOperators = new Operator[NUM_OPERATORS];
			for(int i=0; i<objects.length; i++){
				newOperators[i] = (Operator) objects[i];
			}
			resetOperators(newOperators);
			try{
				Stop activeStop = (Stop) extras.getSerializable(Globals.getStopKey());
				if(activeStop != null){
					controller.changeStop(activeStop);
				}
			}
			catch(Exception e){}
		}
	}
	
	public void operatorClick(final ImageView imageview, final Operator operator ){
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	// deactivate all 
            	for(int i=0; i<NUM_OPERATORS; i++){
            		operators[i].deactivate();
            	}
            	controller.changeActiveOperator(operator, imageview);
            	//activate selected
            	operator.activate();
            }
        });
	}

	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(controller.getCurrentContext(), LiveMapActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.putExtras(createBundle());
            	controller.getCurrentContext().startActivity(i);
            }
        });
	}
	
	public Bundle createBundle(){
		Bundle bundle = new Bundle();  
    	bundle.putSerializable(Globals.getOperatorsKey(), operators);
    	bundle.putSerializable(Globals.getStopKey(), controller.getCurrentStop());
    	return bundle;
	}

	public void onBackPressed() {
		Intent i = new Intent(controller.getCurrentContext(), HomepageActivity.class);
		controller.getCurrentContext().startActivity(i);	
	}
}
