package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class BusEireannOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 3696451285102639725L;
	private final static String OP_CODE="be";
	private final static int INDEX=1;

	public BusEireannOperator(){
		super(OP_CODE, INDEX);
	}
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
	}

}
