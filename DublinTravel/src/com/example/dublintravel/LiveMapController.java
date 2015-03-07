package com.example.dublintravel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.content.Context;
import android.widget.ImageView;

public class LiveMapController extends Controller{
	
	private LiveMapNavigationBar navbar;
	private Operator[] operators;
	private final Lock queryingLock = new ReentrantLock();
	private boolean isQuerying;
	
	public LiveMapController(Context context, LiveMapNavigationBar navbar){
		super(context);
		this.navbar = navbar;
		this.navbar.activate(this);
		operators = new Operator[navbar.getNumOperators()];
		isQuerying = false;
	}
	
	public void changeActiveOperator(Operator operator, ImageView imageView) {
		changeImageViewBorder(imageView);
	}
	
	public void setOperators(Operator[] newOperators){
		this.operators = newOperators;
		getStopLocations();
	}
	
	private void getStopLocations(){
		GetStopLocationThread thread;
		for(int i=0; i< navbar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop() && operators[i].requireAdditionalLocationRequest()){
				thread = new GetStopLocationThread(this);
				thread.execute(operators[i]);
			}
		}
	}
	
	public synchronized void updateLocationOfOperator(Location location, Operator operator){
		Stop tmp;
		for(int i=0; i < navbar.getNumOperators(); i++){
			if(operators[i].equals(operator)){
				tmp = operators[i].getPreviousStop();
				tmp.setLocation(location);
				operators[i].setPreviousStop(tmp);
			}
		}
	}
	
	public Location getStopLocation(int index){
		if(operators[index].hasPreviousStop()){
			return operators[index].getPreviousStop().getLocation();
		}
		return null;
	}
	
	public synchronized boolean isQuerying(){
		boolean toReturn;
		queryingLock.lock();
		try{
			toReturn = isQuerying;
		}
		catch(Exception e){
			toReturn = false;
		}
		finally{
			queryingLock.unlock();
		}
		return toReturn;
	}
	
	public synchronized void setIsQuerying(boolean isQuerying){
		queryingLock.lock();
		try{
			this.isQuerying = isQuerying;
		}
		catch(Exception e){}
		finally{
			queryingLock.unlock();
		}
	}
}
