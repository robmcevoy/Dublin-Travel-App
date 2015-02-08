package com.example.dublintravel;

public abstract class Operator{
	public abstract Parser getParser();
	public abstract String generateUrlString(String stop);
	public abstract boolean needsAuth();
	public abstract String getOperatorCode();
}
