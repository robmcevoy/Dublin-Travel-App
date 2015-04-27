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

/* activity that runs the map dashboard */

public class MapDashboardActivity extends Activity implements OnMapReadyCallback, LocationListener,
GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener{
	
	MapDashboardController controller;
	private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private Location location;
    private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        controller = new MapDashboardController(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Bundle EXTRAS = getIntent().getExtras();
        controller.getNavbar().handleBundle(EXTRAS);
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
		controller.getNavbar().onBackPressed();
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

	
	public MapDashboardController getController(){
		return this.controller;
	}

}
