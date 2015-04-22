package com.example.dublintravel;

import android.app.Activity;
import android.widget.TextView;

public class MapLegendCreator {

	public TextView[] createStopNames(Activity activity){
		TextView busEireannStopName = (TextView) activity.findViewById(R.id.busEireannStopName);
        TextView dublinBusStopName = (TextView)  activity.findViewById(R.id.dublinBusStopName);
        TextView irishRailStopName = (TextView)  activity.findViewById(R.id.irishRailStopName);
        TextView luasStopName = (TextView)  activity.findViewById(R.id.luasStopName);
        TextView[] stopNamesArray = new TextView[NavigationBar.getNumOperators()];
        stopNamesArray[new BusEireannOperator().getIndex()] = busEireannStopName;
        stopNamesArray[new DublinBusOperator().getIndex()] = dublinBusStopName;
        stopNamesArray[new IrishRailOperator().getIndex()] = irishRailStopName;
        stopNamesArray[new LuasOperator().getIndex()] = luasStopName;
        return stopNamesArray;
	}
	
	public TextView[] createDistancesArray(Activity activity){
		TextView busEireannStopDistance = (TextView) activity.findViewById(R.id.busEireannStopDistance);
        TextView dublinBusStopDistance = (TextView) activity.findViewById(R.id.dublinBusStopDistance);
        TextView irishRailStopDistance = (TextView) activity.findViewById(R.id.irishRailStopDistance);
        TextView luasStopDistance = (TextView) activity.findViewById(R.id.luasStopDistance);
        TextView[] stopDistancesArray = new TextView[NavigationBar.getNumOperators()];
        stopDistancesArray[new BusEireannOperator().getIndex()] = busEireannStopDistance;
        stopDistancesArray[new DublinBusOperator().getIndex()] = dublinBusStopDistance;
        stopDistancesArray[new IrishRailOperator().getIndex()] = irishRailStopDistance;
        stopDistancesArray[new LuasOperator().getIndex()] = luasStopDistance;
        return stopDistancesArray;
	}
	
	public TextView[] createWalkArray(Activity activity){
		TextView busEireannStopWalk = (TextView) activity.findViewById(R.id.busEireannStopWalk);
        TextView dublinBusStopWalk = (TextView) activity.findViewById(R.id.dublinBusStopWalk);
        TextView irishRailStopWalk = (TextView) activity.findViewById(R.id.irishRailStopWalk);
        TextView luasStopWalk = (TextView) activity.findViewById(R.id.luasStopWalk);
        TextView[] stopWalksArray = new TextView[NavigationBar.getNumOperators()];
        stopWalksArray[new BusEireannOperator().getIndex()] = busEireannStopWalk;
        stopWalksArray[new DublinBusOperator().getIndex()] = dublinBusStopWalk;
        stopWalksArray[new IrishRailOperator().getIndex()] = irishRailStopWalk;
        stopWalksArray[new LuasOperator().getIndex()] = luasStopWalk;
        return stopWalksArray;
	}
	
	public TextView[] createCycleArray(Activity activity){
		TextView busEireannStopCycle = (TextView) activity.findViewById(R.id.busEireannStopCycling);
        TextView dublinBusStopCycle = (TextView) activity.findViewById(R.id.dublinBusStopCycling);
        TextView irishRailStopCycle = (TextView) activity.findViewById(R.id.irishRailStopCycling);
        TextView luasStopCycle = (TextView) activity.findViewById(R.id.luasStopCycling);
        TextView[] stopCyclesArray = new TextView[NavigationBar.getNumOperators()];
        stopCyclesArray[new BusEireannOperator().getIndex()] = busEireannStopCycle;
        stopCyclesArray[new DublinBusOperator().getIndex()] = dublinBusStopCycle;
        stopCyclesArray[new IrishRailOperator().getIndex()] = irishRailStopCycle;
        stopCyclesArray[new LuasOperator().getIndex()] = luasStopCycle;
        return stopCyclesArray;
	}
	
	public TextView[] createDrivingArray(Activity activity){
		 TextView busEireannStopDriving = (TextView) activity.findViewById(R.id.busEireannStopDriving);
	     TextView dublinBusStopDriving = (TextView) activity.findViewById(R.id.dublinBusStopDriving);
	     TextView irishRailStopDriving = (TextView) activity.findViewById(R.id.irishRailStopDriving);
	     TextView luasStopDriving = (TextView) activity.findViewById(R.id.luasStopDriving);
	     TextView[] stopDrivingsArray = new TextView[NavigationBar.getNumOperators()];
	     stopDrivingsArray[new BusEireannOperator().getIndex()] = busEireannStopDriving;
	     stopDrivingsArray[new DublinBusOperator().getIndex()] = dublinBusStopDriving;
	     stopDrivingsArray[new IrishRailOperator().getIndex()] = irishRailStopDriving;
	     stopDrivingsArray[new LuasOperator().getIndex()] = luasStopDriving;
	     return stopDrivingsArray;
	}
}
