package com.example.dublintravel;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class LiveMapWebViewInterface extends WebViewInterface {
	
	private Context context;
	private LocationManager locationManager;
	private Location current;
	
	public LiveMapWebViewInterface(Context context){
		this.context = context;
		this.locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
	}
	
	@JavascriptInterface
	 public double getLongitude() {
		current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		return current.getLongitude();
	}
	
	@JavascriptInterface
	 public double getLatitude() {
		return current.getLatitude();
	}

}
