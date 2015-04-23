package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* One of the three public transport operators available through the Rtpi Rest Api */

public class LuasOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 7363410576114813569L;
	private final static String OP_CODE = "luas";
	private final static int INDEX=2;
	
	public LuasOperator(){
		super(OP_CODE, INDEX);
	}
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);
	}

}
