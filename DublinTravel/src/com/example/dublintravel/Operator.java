package com.example.dublintravel;

public interface Operator {
	
	public Parser getParser();
	public String generateUrlString(String stop);
	public boolean needsAuth();
}
