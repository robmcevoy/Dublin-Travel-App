package com.example.dublintravel;

import java.util.ArrayList;

public interface Parser {

	public ArrayList<StopInfo> getStopInfo(String data);
	public ArrayList<Stop> getStops(String data);
}
