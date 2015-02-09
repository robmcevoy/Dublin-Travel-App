package com.example.dublintravel;

public abstract class Operator{
	public abstract Parser getParser();
	public abstract String generateRealtimeInfoUrlString(String stop);
	public abstract String generateStopsUrl();
	public abstract boolean needsAuth();
	public abstract String getOperatorCode();
}
