package com.example.dublintravel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/* navigation bar for public transport dashboards,
 * clicking a transport operators does not change the activity
 * but changes the dashboard's content
 */

public class PTDNavigationBar extends NavigationBar {
	
	private PTDController controller;
	
	public PTDNavigationBar(Activity activity){
		super(activity, R.layout.rtpi_dashboard);
	}
	
	public Context getContext() {
		return controller.getCurrentContext();
	}
	
	public void activate(PTDController controller){
		this.controller = controller;
		super.activate();
	}
	
	public void resetOperators(Operator[] newOperators){
		super.resetOperators(newOperators);
		for(int i=0; i<NUM_OPERATORS; i++){
			if(operators[i].isActive()){
				controller.changeActiveOperator(operators[i], imageviews[i]);
			}
		}
	}
	
	public void operatorClick(final ImageView imageview, final Operator operator ){
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	for(int i=0; i<NUM_OPERATORS; i++){
            		operators[i].deactivate();
            	}
            	controller.changeActiveOperator(operator, imageview);
            	operator.activate();
            }
        });
	}

	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getContext(), MapDashboardActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            }
        });
	}
}
