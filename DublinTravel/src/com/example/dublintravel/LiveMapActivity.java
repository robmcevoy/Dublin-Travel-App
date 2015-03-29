package com.example.dublintravel;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class LiveMapActivity extends Activity implements OnMapReadyCallback, LocationListener,
GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener{
	
	LiveMapController controller;
	LiveMapNavigationBar navbar;
	private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private Location location;
    private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_map);
		final ImageView dublinBusImageView = (ImageView) findViewById(R.id.dublinBusLogo);
        final ImageView irishRailImageView = (ImageView) findViewById(R.id.irishRailLogo);
        final ImageView busEireannImageView = (ImageView) findViewById(R.id.busEireannLogo);
        final ImageView luasImageView = (ImageView) findViewById(R.id.luasLogo);
        final ImageView liveMapImageView = (ImageView) findViewById(R.id.liveMapLogo);
        navbar = new LiveMapNavigationBar(dublinBusImageView,luasImageView, 
		irishRailImageView,busEireannImageView, liveMapImageView);
        WebView webview = (WebView) findViewById(R.id.twitterFeed);
        TextView busEireannStopName = (TextView) findViewById(R.id.busEireannStopName);
        TextView dublinBusStopName = (TextView) findViewById(R.id.dublinBusStopName);
        TextView irishRailStopName = (TextView) findViewById(R.id.irishRailStopName);
        TextView luasStopName = (TextView) findViewById(R.id.luasStopName);
        TextView busEireannStopDistance = (TextView) findViewById(R.id.busEireannStopDistance);
        TextView dublinBusStopDistance = (TextView) findViewById(R.id.dublinBusStopDistance);
        TextView irishRailStopDistance = (TextView) findViewById(R.id.irishRailStopDistance);
        TextView luasStopDistance = (TextView) findViewById(R.id.luasStopDistance);
        TextView busEireannStopWalk = (TextView) findViewById(R.id.busEireannStopWalk);
        TextView dublinBusStopWalk = (TextView) findViewById(R.id.dublinBusStopWalk);
        TextView irishRailStopWalk = (TextView) findViewById(R.id.irishRailStopWalk);
        TextView luasStopWalk = (TextView) findViewById(R.id.luasStopWalk);
        TextView busEireannStopCycle = (TextView) findViewById(R.id.busEireannStopCycling);
        TextView dublinBusStopCycle = (TextView) findViewById(R.id.dublinBusStopCycling);
        TextView irishRailStopCycle = (TextView) findViewById(R.id.irishRailStopCycling);
        TextView luasStopCycle = (TextView) findViewById(R.id.luasStopCycling);
        TextView busEireannStopDriving = (TextView) findViewById(R.id.busEireannStopDriving);
        TextView dublinBusStopDriving = (TextView) findViewById(R.id.dublinBusStopDriving);
        TextView irishRailStopDriving = (TextView) findViewById(R.id.irishRailStopDriving);
        TextView luasStopDriving = (TextView) findViewById(R.id.luasStopDriving);
        TextView[] stopNamesArray = new TextView[navbar.getNumOperators()];
        TextView[] stopDistancesArray = new TextView[navbar.getNumOperators()];
        TextView[] stopWalksArray = new TextView[navbar.getNumOperators()];
        TextView[] stopCyclesArray = new TextView[navbar.getNumOperators()];
        TextView[] stopDrivingsArray = new TextView[navbar.getNumOperators()];
        stopNamesArray[new BusEireannOperator().getIndex()] = busEireannStopName;
        stopWalksArray[new BusEireannOperator().getIndex()] = busEireannStopWalk;
        stopDistancesArray[new BusEireannOperator().getIndex()] = busEireannStopDistance;
        stopCyclesArray[new BusEireannOperator().getIndex()] = busEireannStopCycle;
        stopDrivingsArray[new BusEireannOperator().getIndex()] = busEireannStopDriving;
        stopNamesArray[new DublinBusOperator().getIndex()] = dublinBusStopName;
        stopDistancesArray[new DublinBusOperator().getIndex()] = dublinBusStopDistance;
        stopWalksArray[new DublinBusOperator().getIndex()] = dublinBusStopWalk;
        stopCyclesArray[new DublinBusOperator().getIndex()] = dublinBusStopCycle;
        stopDrivingsArray[new DublinBusOperator().getIndex()] = dublinBusStopDriving;
        stopNamesArray[new IrishRailOperator().getIndex()] = irishRailStopName;
        stopDistancesArray[new IrishRailOperator().getIndex()] = irishRailStopDistance;
        stopWalksArray[new IrishRailOperator().getIndex()] = irishRailStopWalk;
        stopCyclesArray[new IrishRailOperator().getIndex()] = irishRailStopCycle;
        stopDrivingsArray[new IrishRailOperator().getIndex()] = irishRailStopDriving;
        stopNamesArray[new LuasOperator().getIndex()] = luasStopName;
        stopWalksArray[new LuasOperator().getIndex()] = luasStopWalk;
        stopDistancesArray[new LuasOperator().getIndex()] = luasStopDistance;
        stopCyclesArray[new LuasOperator().getIndex()] = luasStopCycle;
        stopDrivingsArray[new LuasOperator().getIndex()] = luasStopDriving;
        controller = new LiveMapController(this, navbar,stopNamesArray,stopDistancesArray, stopWalksArray, stopCyclesArray, stopDrivingsArray, webview);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
		
		// handle bundle
        final Bundle EXTRAS = getIntent().getExtras();
        navbar.handleBundle(EXTRAS);
        
        //location
        locationRequest = LocationRequest.create();
        googleApiClient = new GoogleApiClient.Builder(this)
        .addApi(LocationServices.API)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.live_map, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		navbar.onBackPressed();
	}

	
	@Override
	public void onMapReady(final GoogleMap googleMap) {
		
		googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
		    @Override
		    public void onMapLoaded() {
		    	controller.setMap(googleMap);
		    }
		});

	}

	@Override
	public void onLocationChanged(Location location) {
		//TODO react when location changes
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {}

	@Override
	public void onConnected(Bundle arg0) {
		Location currentLocation = fusedLocationProviderApi.getLastLocation(googleApiClient);
        if (currentLocation != null) {
            location = currentLocation;
            controller.setLocation(location);
        }
        else{
        	fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
	}

	@Override
	public void onConnectionSuspended(int cause) {}

}
