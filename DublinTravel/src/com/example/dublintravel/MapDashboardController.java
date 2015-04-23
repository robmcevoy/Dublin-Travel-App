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
import android.app.Activity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* Controls all logic, reacts to user input, screen rotation and device size
 * and updates real time visualizations for map dashboards
 */

public class MapDashboardController extends Controller{
	
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
	private TextView[] stopWalks;
	private TextView[] stopCycles;
	private TextView[] stopDrivings;
	private boolean gotStopLocations;
	private TwitterFeed twitterFeed;
	private MapDashboardNavigationBar navbar;
	
	public MapDashboardController(Activity activity){
		super(activity);
		this.markers = new ArrayList<MarkerOptions>();
		MapLegendCreator creator = new MapLegendCreator();
		this.stopNames = creator.createStopNames(activity);
		this.stopDistances = creator.createDistancesArray(activity);
		this.stopWalks = creator.createWalkArray(activity);
		this.stopCycles = creator.createCycleArray(activity);
		this.stopDrivings = creator.createDrivingArray(activity);
		this.gotStopLocations = false;
		this.twitterFeed = new TwitterFeed((WebView) activity.findViewById(R.id.twitterFeed), this);
		this.twitterFeed.start();
		operators = new Operator[NavigationBar.getNumOperators()];
		numQueryingLocations = 0;
		this.navbar = new MapDashboardNavigationBar(activity);
		this.navbar.activate(this);
		configureSmallScreen();
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
			gotStopLocations = true;
			if(shouldGetTravelTimes()){
				getTravelTimes();
			}
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
		for(int i=0; i< NavigationBar.getNumOperators(); i++){
			if(operators[i].shouldSendLocationRequest()){
				thread = new GetStopLocationThread(this);
				thread.execute(operators[i]);
				count++;
			}
		}
		setNumQueryingLocations(count);
	}
	
	private void setStopNames(){
		for(int i=0; i < NavigationBar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop()){
				stopNames[i].setText(operators[i].getPreviousStop().getName());
			}
		}
	}
	
	public synchronized void updateLocationOfOperator(Location location, Operator operator){
		Stop tmp;
		for(int i=0; i < NavigationBar.getNumOperators(); i++){
			if(operators[i].equals(operator)){
				tmp = operators[i].getPreviousStop();
				tmp.setLocation(location);
				operators[i].setPreviousStop(tmp);
				navbar.setOperatorWithLocation(operators[i]);
				i = NavigationBar.getNumOperators();
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
		for(int i=0; i< NavigationBar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop()){
				thread = new GetTravelTimesThread(currentLocation, operators[i].getPreviousStop());
				thread.execute(stopDistances[i], stopWalks[i], stopCycles[i], stopDrivings[i]);
			}
		}
	}
	
	public MapDashboardNavigationBar getNavbar(){
		return this.navbar;
	}

	public void configureSmallScreen() {
		if(!isLargeScreen()){
			LinearLayout lastMapLegendItem = (LinearLayout) activity.findViewById(R.id.lastMapLegendItem);
			LinearLayout twitterLayout = (LinearLayout) activity.findViewById(R.id.twitterFeedLayout);
			LinearLayout mapLegendLayout = (LinearLayout) activity.findViewById(R.id.mapLegendLayout);
			LinearLayout map = (LinearLayout) activity.findViewById(R.id.mapLayout);
			configureHorizontalScrollView(activity, map, mapLegendLayout, twitterLayout);
			lastMapLegendItem.setBackground(activity.getResources().getDrawable(R.drawable.rounded_corner_orange_no_bottom));
		}
		
	}
}
