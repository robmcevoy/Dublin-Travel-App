package com.example.dublintravel;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


public class IrishRailOperator extends RtpiXmlOperator {

	private static final long serialVersionUID = 4362786347952215799L;
	private final static String OP_CODE="ir";
	private final static int INDEX=3;
	
	public IrishRailOperator(){
		super(OP_CODE, INDEX);
	}
	
	public BitmapDescriptor getMarkerColor(Controller controller){
		MapsInitializer.initialize(controller.getCurrentContext());
		return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
	}
}
