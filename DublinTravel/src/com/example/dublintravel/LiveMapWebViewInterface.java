package com.example.dublintravel;

import android.location.LocationManager;
import android.webkit.JavascriptInterface;

public class LiveMapWebViewInterface extends WebViewInterface {
	
	private LocationManager locationManager;
	private android.location.Location current;
	private Location dublinBus;
	private Location luas;
	private Location busEireann;
	private Location irishRail;
	private LiveMapController liveMapController;
	
	public LiveMapWebViewInterface(LiveMapController liveMapController){
		super(liveMapController);
		this.liveMapController = liveMapController;
		this.locationManager = (LocationManager) controller.getCurrentContext().getSystemService(controller.getCurrentContext().LOCATION_SERVICE);
	}
	
	@JavascriptInterface
	public void updateLocations(){
		current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		dublinBus = liveMapController.getStopLocation(new DublinBusOperator().getIndex());
		luas =  liveMapController.getStopLocation(new LuasOperator().getIndex());
		busEireann = liveMapController.getStopLocation(new BusEireannOperator().getIndex());
		irishRail = liveMapController.getStopLocation(new IrishRailOperator().getIndex());
	}
	
	@JavascriptInterface
	public boolean hasCurrentLocation() {
		return current != null;
	}
	
	@JavascriptInterface
	public double getCurrentLongitude() {
		return current.getLongitude();
	}
	
	@JavascriptInterface
	public double getCurrentLatitude() {
		return current.getLatitude();
	}
	
	@JavascriptInterface
	public boolean isQuerying(){
		return liveMapController.isQuerying();
	}
	
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
