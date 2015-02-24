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
    private final String OPERATOR = "operator";

	
	public NavigationBar(	ImageView dublinBusImageView, ImageView luasImageView, 
							ImageView irishRailImageView, ImageView busEireannImageView){
		this.dublinBusImageView = dublinBusImageView;
		this.luasImageView = luasImageView;
		this.irishRailImageView = irishRailImageView;
		this.busEireannImageView = busEireannImageView;
		dublinBusOperator = new DublinBusOperator();
		irishRailOperator = new IrishRailOperator();
		busEireannOperator = new BusEireannOperator();
		luasOperator = new LuasOperator();
	}
	
	public void activate(RtpiController rtpiController){
		this.rtpiController = rtpiController;
		operatorClick(irishRailImageView,irishRailOperator, rtpiController );
	    operatorClick(luasImageView,luasOperator, rtpiController );
	    operatorClick(busEireannImageView,busEireannOperator, rtpiController );
	    operatorClick(dublinBusImageView,dublinBusOperator, rtpiController );
	}
	
	public void getOpFromHomepage(Bundle extras){
		Operator selected;
		if(extras != null){
			selected = (Operator) extras.getSerializable(OPERATOR);
			onOperatorChange(selected);
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
	
	public void onOperatorChange(Operator operator){
		if(operator.equals(dublinBusOperator))
			rtpiController.changeOperator(dublinBusOperator, dublinBusImageView);
		else if(operator.equals(luasOperator))
			rtpiController.changeOperator(luasOperator, luasImageView);
		else if(operator.equals(busEireannOperator))
			rtpiController.changeOperator(busEireannOperator, busEireannImageView);
		else
			rtpiController.changeOperator(irishRailOperator, irishRailImageView);
	}
}
