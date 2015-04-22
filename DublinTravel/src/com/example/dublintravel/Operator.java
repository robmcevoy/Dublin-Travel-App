package com.example.dublintravel;

import java.io.Serializable;
import com.google.android.gms.maps.model.BitmapDescriptor;

public abstract class Operator implements Serializable{
	
	private static final long serialVersionUID = -3463204625750886206L;
	private Stop previousStop;
	protected String op_code;
	protected int index;
	protected boolean isActive;
	protected boolean needsAuth;
	protected Parser parser;
	protected boolean requireLocationRequest;
	private final int numPossibleOperators = 4;
	public abstract String generateRealtimeInfoUrlString(String stop);
	public abstract String generateStopsUrl();
	public abstract String generateStopLocationUrl(String stop);
	public abstract BitmapDescriptor getMarkerColor(Controller controller);
	
	protected Operator(String op_code, int index, boolean needsAuth, Parser parser, boolean requireLocationRequest){
		isActive = false;
		this.op_code = op_code;
		this.index = index;
		this.needsAuth = needsAuth;
		this.parser = parser;
		this.requireLocationRequest = requireLocationRequest;
	}
	
	public boolean equals(Operator operator){
		if(getOperatorCode().equals(operator.getOperatorCode())){
			return true;
		}
		return false;
	}
	
	public boolean hasPreviousStop(){
		return (previousStop != null);
	}
	
	public Stop getPreviousStop(){
		return previousStop;
	}
	
	public void setPreviousStop(Stop previousStop){
		this.previousStop = previousStop;
	}
	
	public String getOperatorCode(){
		return op_code;
	}	
	
	public int getIndex(){
		return index;
	}
	
	public void activate(){
		isActive = true;
	}
	
	public void deactivate(){
		isActive = false;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public int getNumPossibleOperators(){
		return numPossibleOperators;
	}
	
	public boolean shouldSendLocationRequest(){
		return hasPreviousStop() && requireAdditionalLocationRequest();
	}
	
	public boolean requireAdditionalLocationRequest(){
		return requireLocationRequest;
	}
	
	public boolean needsAuth(){
		return needsAuth;
	}
	
	public Parser getParser(){
		return parser;
	}
}