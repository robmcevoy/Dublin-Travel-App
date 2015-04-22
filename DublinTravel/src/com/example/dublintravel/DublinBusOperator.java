package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


public class DublinBusOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 5664488531244306417L;
	private final static String OP_CODE="bac";
	private final static int INDEX=0;
	
	public DublinBusOperator(){
		super(OP_CODE, INDEX);
	}
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
	}

}
