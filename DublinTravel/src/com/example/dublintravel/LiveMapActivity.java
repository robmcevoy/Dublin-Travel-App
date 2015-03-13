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
import android.widget.Toast;

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
        controller = new LiveMapController(this, navbar);
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
