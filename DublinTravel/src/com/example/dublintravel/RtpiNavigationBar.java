package com.example.dublintravel;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class RtpiNavigationBar extends NavigationBar {
	
	private RtpiController controller;
	
	public RtpiNavigationBar(	ImageView dublinBusImageView,
								ImageView luasImageView, ImageView irishRailImageView,
								ImageView busEireannImageView, ImageView mapImageView) {
		super(dublinBusImageView, luasImageView, irishRailImageView,busEireannImageView, mapImageView);
	}
	
	@Override
	public Context getContext() {
		return controller.getCurrentContext();
	}
	
	public void activate(RtpiController controller){
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
            	Intent i = new Intent(getContext(), LiveMapActivity.class);
            	i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            }
        });
	}
}
