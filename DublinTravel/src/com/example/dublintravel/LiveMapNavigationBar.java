package com.example.dublintravel;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LiveMapNavigationBar extends NavigationBar {
	
	private LiveMapController controller;
	private Stop activeStopOnCreate;
	private Operator activeOperatorOnCreate;

	public LiveMapNavigationBar(ImageView dublinBusImageView,
								ImageView luasImageView, ImageView irishRailImageView,
								ImageView busEireannImageView, ImageView mapImageView) {
		super(dublinBusImageView, luasImageView, irishRailImageView,
				busEireannImageView, mapImageView);
	}
	
	public void activate(LiveMapController controller){
		this.controller = controller;
		super.activate();
		controller.changeActiveOperator(null, mapImageView);
	}

	@Override
	public void operatorClick(ImageView imageview, final Operator operator) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				if(!operator.equals(activeOperatorOnCreate)){
					activeStopOnCreate = null;
					if(operator.hasPreviousStop()){
						activeStopOnCreate = operator.getPreviousStop();
					}
				}
				for(int i=0; i<NUM_OPERATORS; i++){
					operators[i].deactivate();
				}
				operator.activate();
				Bundle bundle = new Bundle();  
            	bundle.putSerializable(Globals.getOperatorsKey(), operators);
            	if(activeStopOnCreate != null){
            		bundle.putSerializable(Globals.getStopKey(), activeStopOnCreate);
            	}
            	Intent i = new Intent(controller.getCurrentContext(), RtpiDashboardActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.putExtras(bundle);
            	controller.getCurrentContext().startActivity(i);
            	operator.deactivate();
            }
		});
		
	}

	@Override
	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				// do nothing
            }
		});
		
	}

	@Override
	public void handleBundle(Bundle extras) {
		if(extras != null){
			Object[] objects = (Object[]) extras.getSerializable(Globals.getOperatorsKey());
			Operator[] newOperators = new Operator[NUM_OPERATORS];
			for(int i=0; i<objects.length; i++){
				newOperators[i] = (Operator) objects[i];
			}
			resetOperators(newOperators);
			for(int i=0; i<NUM_OPERATORS; i++){
				if(operators[i].isActive){
					activeOperatorOnCreate = operators[i];
				}
			}
			try{
				activeStopOnCreate = (Stop) extras.getSerializable(Globals.getStopKey());
			}
			catch(Exception e){}
		}
	}

	public void onBackPressed() {
		Intent i = new Intent(controller.getCurrentContext(), HomepageActivity.class);
		controller.getCurrentContext().startActivity(i);
	}

}
