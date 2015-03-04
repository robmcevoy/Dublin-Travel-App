package com.example.dublintravel;

import java.io.Serializable;

public abstract class Operator implements Serializable{
	
	private static final long serialVersionUID = -3463204625750886206L;
	private Stop previousStop;
	protected String op_code;
	protected int index;
	protected boolean isActive;
	public abstract Parser getParser();
	public abstract String generateRealtimeInfoUrlString(String stop);
	public abstract String generateStopsUrl();
	public abstract boolean needsAuth();
	
	protected Operator(){
		isActive = false;
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
	
	
}
