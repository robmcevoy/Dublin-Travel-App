package com.example.dublintravel;

import java.io.Serializable;

public abstract class Operator implements Serializable{
	private static final long serialVersionUID = -3463204625750886206L;
	public abstract Parser getParser();
	public abstract String generateRealtimeInfoUrlString(String stop);
	public abstract String generateStopsUrl();
	public abstract boolean needsAuth();
	public abstract String getOperatorCode();
	
	public boolean equals(Operator operator){
		if(getOperatorCode().equals(operator.getOperatorCode())){
			return true;
		}
		return false;
	}
}
