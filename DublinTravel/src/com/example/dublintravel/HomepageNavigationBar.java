package com.example.dublintravel;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class HomepageNavigationBar extends NavigationBar {
	
	private Context context;

	public HomepageNavigationBar(ImageView dublinBusImageView,
			ImageView luasImageView, ImageView irishRailImageView,
			ImageView busEireannImageView, ImageView mapImageView, Context context) {
		super(dublinBusImageView, luasImageView, irishRailImageView,
				busEireannImageView, mapImageView);
		this.context = context;
	}
	
	@Override
	public Context getContext() {
		return context;
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
            	Intent i = new Intent(getContext(), LiveMapActivity.class);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            }
        });
	}

}
