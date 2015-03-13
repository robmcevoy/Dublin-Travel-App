package com.example.dublintravel;

import android.webkit.JavascriptInterface;

public class LiveMapWebViewInterface extends WebViewInterface {

	private Location dublinBus;
	private Location luas;
	private Location busEireann;
	private Location irishRail;
	private LiveMapController liveMapController;
	
	public LiveMapWebViewInterface(LiveMapController liveMapController){
		super(liveMapController);
		this.liveMapController = liveMapController;
	}
	
	@JavascriptInterface
	public void updateLocations(){
		dublinBus = liveMapController.getStopLocation(new DublinBusOperator().getIndex());
		luas =  liveMapController.getStopLocation(new LuasOperator().getIndex());
		busEireann = liveMapController.getStopLocation(new BusEireannOperator().getIndex());
		irishRail = liveMapController.getStopLocation(new IrishRailOperator().getIndex());
	}
	/*
	@JavascriptInterface
	public boolean isQuerying(){
		return liveMapController.isQuerying();
	}
	*/
	@JavascriptInterface
	public boolean hasDublinBusLocation() {
		return dublinBus != null;
	}
	
	@JavascriptInterface
	public double getDublinBusLatitude(){
		return dublinBus.getLatitude();
	}
	
	@JavascriptInterface
	public double getDublinBusLongitude(){
		return dublinBus.getLongitude();
	}
	
	@JavascriptInterface
	public boolean hasIrishRailLocation() {
		return irishRail != null;
	}
	
	@JavascriptInterface
	public double getIrishRailLatitude(){
		return irishRail.getLatitude();
	}
	
	@JavascriptInterface
	public double getIrishRailLongitude(){
		return irishRail.getLongitude();
	}
	
	@JavascriptInterface
	public boolean hasLuasLocation() {
		return luas != null;
	}
	
	@JavascriptInterface
	public double getLuasLatitude(){
		return luas.getLatitude();
	}
	
	@JavascriptInterface
	public double getLuasLongitude(){
		return luas.getLongitude();
	}
	
	@JavascriptInterface
	public boolean hasBusEireannLocation() {
		return busEireann != null;
	}
	
	@JavascriptInterface
	public double getBusEireannLatitude(){
		return busEireann.getLatitude();
	}
	
	@JavascriptInterface
	public double getBusEireannLongitude(){
		return busEireann.getLongitude();
	}
}
