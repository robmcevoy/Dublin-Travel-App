package com.example.dublintravel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/* navigation bar for map dashboards,
 * clicking a transport operators changes the activity
 * to the public transport dashboard 
 */

public class MapDashboardNavigationBar extends NavigationBar {
	
	private MapDashboardController controller;
	
	public MapDashboardNavigationBar(Activity activity){
		super(activity);
	}
	
	public void activate(MapDashboardController controller){
		this.controller =  controller;
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

	public void operatorClick(ImageView imageview, final Operator operator) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				for(int i=0; i<NUM_OPERATORS; i++){
					operators[i].deactivate();
				}
				operator.activate();
            	Intent i = new Intent(getContext(), PTDActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            	operator.deactivate();
            }
		});
		
	}

	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				// do nothing, already on page
            }
		});
		
	}

}
