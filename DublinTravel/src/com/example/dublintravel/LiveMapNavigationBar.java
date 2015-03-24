package com.example.dublintravel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class LiveMapNavigationBar extends NavigationBar {
	
	private LiveMapController controller;

	public LiveMapNavigationBar(ImageView dublinBusImageView,
								ImageView luasImageView, ImageView irishRailImageView,
								ImageView busEireannImageView, ImageView mapImageView) {
		super(dublinBusImageView, luasImageView, irishRailImageView,
				busEireannImageView, mapImageView);
	}
	
	public Context getContext() {
		return controller.getCurrentContext();
	}
	
	public void activate(LiveMapController controller){
		this.controller = controller;
		super.activate();
		controller.changeActiveOperator(null, mapImageView);
	}
	
	public void resetOperators(Operator[] newOperators){
		super.resetOperators(newOperators);
		controller.setOperators(operators);
	}
	
	public void setOperatorWithLocation(Operator withLocation){
		operators[withLocation.getIndex()] = withLocation;
	}

	@Override
	public void operatorClick(ImageView imageview, final Operator operator) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				for(int i=0; i<NUM_OPERATORS; i++){
					operators[i].deactivate();
				}
				operator.activate();
            	Intent i = new Intent(getContext(), RtpiDashboardActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
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

}
