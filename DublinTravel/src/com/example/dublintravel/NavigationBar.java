package com.example.dublintravel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NavigationBar {
	
    private ImageView dublinBusImageView;
    private ImageView irishRailImageView;
    private ImageView busEireannImageView;
    private ImageView luasImageView;
    private RtpiXmlOperator irishRailOperator;
    private BusEireannOperator busEireannOperator;
    private LuasOperator luasOperator;
    private DublinBusOperator dublinBusOperator;
    private RtpiController rtpiController;

	
	public NavigationBar(	ImageView dublinBusImageView, ImageView luasImageView, 
							ImageView irishRailImageView, ImageView busEireannImageView,
							RtpiController rtpiController){
		this.dublinBusImageView = dublinBusImageView;
		this.luasImageView = luasImageView;
		this.irishRailImageView = irishRailImageView;
		this.busEireannImageView = busEireannImageView;
		dublinBusOperator = new DublinBusOperator();
		irishRailOperator = new IrishRailOperator();
		busEireannOperator = new BusEireannOperator();
		luasOperator = new LuasOperator();
		this.rtpiController = rtpiController;
	}
	
	public void activate(){
		operatorClick(irishRailImageView,irishRailOperator, rtpiController );
	    operatorClick(luasImageView,luasOperator, rtpiController );
	    operatorClick(busEireannImageView,busEireannOperator, rtpiController );
	    operatorClick(dublinBusImageView,dublinBusOperator, rtpiController );
	}
	
	public void getOpFromHomepage(Bundle extras){
		String  selected;
		if (extras != null) {
			selected = (String) extras.get("operator");
			if(selected.equals(dublinBusOperator.getOperatorCode()))
				rtpiController.changeOperator(dublinBusOperator, dublinBusImageView);
			else if(selected.equals(luasOperator.getOperatorCode()))
				rtpiController.changeOperator(luasOperator, luasImageView);
			else if(selected.equals(busEireannOperator.getOperatorCode()))
				rtpiController.changeOperator(busEireannOperator, busEireannImageView);
			else
				rtpiController.changeOperator(irishRailOperator, irishRailImageView);
		}
	}
	
	public void operatorClick(final ImageView imageview, final Operator operator, final RtpiController rtpiController ){
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	rtpiController.changeOperator(operator, imageview);
            }
        });
	}

}
