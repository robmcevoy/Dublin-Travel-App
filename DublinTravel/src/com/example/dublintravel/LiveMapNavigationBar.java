package com.example.dublintravel;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LiveMapNavigationBar extends NavigationBar {
	
	private LiveMapController controller;

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
	public void operatorClick(ImageView imageview, Operator operator) {
		// TODO go back to RtpiDashboard Activity
		
	}

	@Override
	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				// Do Nothing 
            }
		});
		
	}

	@Override
	public void handleBundle(Bundle extras) {
		if(extras != null){

		}
		
	}

}
