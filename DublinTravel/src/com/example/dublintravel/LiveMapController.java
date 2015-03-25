package com.example.dublintravel;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LiveMapController extends Controller{
	
	private LiveMapNavigationBar navbar;
	private Operator[] operators;
	private final Lock queryingLock = new ReentrantLock();
	private int numQueryingLocations;
	private GoogleMap googleMap;
	private ArrayList<MarkerOptions> markers;
	private final int PADDING = 60;
	private final LatLng IRELAND = new LatLng(53.484786, -7.701890);
	private final int ZOOM = 6;
	private android.location.Location currentLocation;
	private TextView[] stopNames;
	private TextView[] stopDistances;
	private TravelTimes travelTimes;
	private boolean gotStopLocations;
	
	public LiveMapController(Context context, LiveMapNavigationBar navbar, TextView[] stopNames, TextView[] stopDistances){
		super(context);
		this.navbar = navbar;
		this.navbar.activate(this);
		this.markers = new ArrayList<MarkerOptions>();
		this.stopNames = stopNames;
		this.stopDistances = stopDistances;
		this.travelTimes = new TravelTimes();
		this.gotStopLocations = false;
		operators = new Operator[navbar.getNumOperators()];
		numQueryingLocations = 0;
	}
	
	public void changeActiveOperator(Operator operator, ImageView imageView) {
		changeImageViewBorder(imageView);
	}
	
	public void setOperators(Operator[] newOperators){
		this.operators = newOperators;
		setStopNames();
	}
	
	public void setMap(GoogleMap googleMap){
		this.googleMap = googleMap;
		this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IRELAND, ZOOM));
		this.googleMap.setMyLocationEnabled(true);
		this.googleMap.setTrafficEnabled(true);
		Operator irishRailOp = new IrishRailOperator();
		if(operators[irishRailOp.getIndex()].hasPreviousStop()){
			MarkerOptions marker = getOperatorLocationMarker(operators[irishRailOp.getIndex()]);
			markers.add(marker);
			this.googleMap.addMarker(marker);
		}
		boolean moreLocationsToGet = false;
		for(int i=0; i<operators.length; i++){
			if(i != irishRailOp.getIndex()){
				if(operators[i].hasPreviousStop()){
					moreLocationsToGet = true;
				}
			}
		}
		if(!moreLocationsToGet){
			setMapBounds();
		}
		getStopLocations();
	}
	
	public void setLocation(android.location.Location location){
		this.currentLocation = location;
		if(shouldGetTravelTimes()){
			getTravelTimes();
		}
	}
	
	public GoogleMap getMap(){
		return this.googleMap;
	}
	
	private void getStopLocations(){
		int count = 0;
		GetStopLocationThread thread;
		for(int i=0; i< navbar.getNumOperators(); i++){
			if(operators[i].shouldSendLocationRequest()){
				thread = new GetStopLocationThread(this);
				thread.execute(operators[i]);
				count++;
			}
		}
		setNumQueryingLocations(count);
	}
	
	private void setStopNames(){
		for(int i=0; i < navbar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop()){
				stopNames[i].setText(operators[i].getPreviousStop().getName());
			}
		}
	}
	
	public synchronized void updateLocationOfOperator(Location location, Operator operator){
		Stop tmp;
		for(int i=0; i < navbar.getNumOperators(); i++){
			if(operators[i].equals(operator)){
				tmp = operators[i].getPreviousStop();
				tmp.setLocation(location);
				operators[i].setPreviousStop(tmp);
				navbar.setOperatorWithLocation(operators[i]);
				i = navbar.getNumOperators();
			}
		}
	}
	
	public MarkerOptions getOperatorLocationMarker(Operator operator){
		LatLng latlng = new LatLng(0,0);
		Location location;
		for(int i=0; i<operators.length; i++){
			if(operator.equals(operators[i]) && operators[i].hasPreviousStop()){
				location = operators[i].getPreviousStop().getLocation();
				latlng = new LatLng(location.getLatitude(), location.getLongitude());
			}
		}
		MarkerOptions marker = new MarkerOptions().position(latlng).icon(operator.getMarkerColor(this));
		markers.add(marker);
		return marker;
	}
	
	public Location getStopLocation(int index){
		if(operators[index].hasPreviousStop()){
			return operators[index].getPreviousStop().getLocation();
		}
		return null;
	}
	
	private void setMapBounds(){
		LatLngBounds.Builder builder = new LatLngBounds.Builder();	
		for (MarkerOptions marker : markers) {
		   	builder.include(marker.getPosition());
		}
		builder.include(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
		LatLngBounds bounds = builder.build();
		CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, PADDING);
		this.googleMap.animateCamera(cu);
	}
	
	public void decNumQueryingLocations(){
		int tmp = -1;
		try{
			queryingLock.lock();
			numQueryingLocations--;
			tmp = numQueryingLocations;
			if(tmp == 0){
				setMapBounds();
				gotStopLocations = true;
				if(shouldGetTravelTimes()){
					getTravelTimes();
				}
			}
		}
		catch(Exception e){}
		finally{
			queryingLock.unlock();
		}
	}
	
	private void setNumQueryingLocations(int n){
		try{
			queryingLock.lock();
			numQueryingLocations=n;
		}
		catch(Exception e){}
		finally{
			queryingLock.unlock();
		}
	}
	
	private boolean shouldGetTravelTimes(){
		return this.gotStopLocations && (this.currentLocation != null);
	}
	
	private void getTravelTimes(){
		GetTravelTimesThread thread;
		for(int i=0; i< navbar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop()){
				thread = new GetTravelTimesThread(travelTimes, currentLocation, operators[i].getPreviousStop());
				thread.execute(stopDistances[i]);
			}
		}
	}
}
