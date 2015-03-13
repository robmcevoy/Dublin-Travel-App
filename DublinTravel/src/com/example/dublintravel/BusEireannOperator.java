package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class BusEireannOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 3696451285102639725L;
	private final String OP_CODE="be";
	private final int INDEX=1;

	public BusEireannOperator(){
		op_code=OP_CODE;
		index = INDEX;
	}
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
	}

}
