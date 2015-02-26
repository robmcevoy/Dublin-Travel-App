package com.example.dublintravel;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NavigationBar {
	
    private ImageView dublinBusImageView;
    private ImageView irishRailImageView;
    private ImageView busEireannImageView;
    private ImageView luasImageView;
    private IrishRailOperator irishRailOperator;
    private BusEireannOperator busEireannOperator;
    private LuasOperator luasOperator;
    private DublinBusOperator dublinBusOperator;
    private RtpiController rtpiController;
    private final String OPERATOR = "active_operator";
  
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
		setOperatorClicks();
	}
	
	public void setOperatorClicks(){
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
            	rtpiController.changeActiveOperator(operator, imageview);
            }
        });
	}
	
	public void onOperatorChange(Operator operator){
		if(operator.equals(dublinBusOperator))
			rtpiController.changeActiveOperator(dublinBusOperator, dublinBusImageView);
		else if(operator.equals(luasOperator))
			rtpiController.changeActiveOperator(luasOperator, luasImageView);
		else if(operator.equals(busEireannOperator))
			rtpiController.changeActiveOperator(busEireannOperator, busEireannImageView);
		else
			rtpiController.changeActiveOperator(irishRailOperator, irishRailImageView);
	}
	
	public void resetOperators(ArrayList<Operator> array){
		for(Operator tmp: array){
			if(tmp.equals(dublinBusOperator)){
				dublinBusOperator =  (DublinBusOperator) tmp;
			}
			else if(tmp.equals(busEireannOperator)){
				busEireannOperator = (BusEireannOperator) tmp;
			}
			else if(tmp.equals(irishRailOperator)){
				irishRailOperator = (IrishRailOperator) tmp;
			}
			else if(tmp.equals(luasOperator)){
				luasOperator = (LuasOperator) tmp;
			}
		}
		setOperatorClicks();
	}
	
	public ArrayList<Operator> getOperators(){
		ArrayList<Operator> array = new ArrayList<Operator>();
		array.add(dublinBusOperator);
		array.add(busEireannOperator);
		array.add(luasOperator);
		array.add(irishRailOperator);
		return array;
	}
	
}
