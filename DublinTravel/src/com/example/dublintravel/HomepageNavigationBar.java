package com.example.dublintravel;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	public void operatorClick(ImageView imageview, final Operator operator) {
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	operator.activate();
            	Bundle bundle = new Bundle();  
            	bundle.putSerializable(Globals.getOperatorsKey(), operators);
            	Intent i = new Intent(context, RtpiDashboardActivity.class);
            	i.putExtras(bundle);
            	context.startActivity(i);
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
            	
            }
        });
	}

	public void handleBundle(Bundle extras) {
		// homepage will never receive a bundle
	}


}
