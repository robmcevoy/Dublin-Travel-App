package com.example.dublintravel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.content.Context;
import android.widget.ImageView;

public class LiveMapController extends Controller{
	
	private LiveMapNavigationBar navbar;
	private Operator[] operators;
	private final Lock queryingLock = new ReentrantLock();
	private int numQueryingLocations;
	
	public LiveMapController(Context context, LiveMapNavigationBar navbar){
		super(context);
		this.navbar = navbar;
		this.navbar.activate(this);
		operators = new Operator[navbar.getNumOperators()];
		numQueryingLocations = 0;
	}
	
	public void changeActiveOperator(Operator operator, ImageView imageView) {
		changeImageViewBorder(imageView);
	}
	
	public void setOperators(Operator[] newOperators){
		this.operators = newOperators;
		getStopLocations();
	}
	
	private void getStopLocations(){
		int count = 0;
		GetStopLocationThread thread;
		for(int i=0; i< navbar.getNumOperators(); i++){
			if(operators[i].hasPreviousStop() && operators[i].requireAdditionalLocationRequest()){
				thread = new GetStopLocationThread(this);
				thread.execute(operators[i]);
				count++;
			}
		}
		setNumQueryingLocations(count);
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
	
	public boolean isQuerying(){
		boolean answer = false;
		try{
			queryingLock.lock();
			answer = numQueryingLocations != 0;
		}
		catch(Exception e){
			answer = false;
		}
		finally{
			queryingLock.unlock();
		}
		return answer;
	}
	
	public void decNumQueryingLocations(){
		try{
			queryingLock.lock();
			numQueryingLocations--;
		}
		catch(Exception e){}
		finally{
			queryingLock.unlock();
		}
	}
	
	private void setNumQueryingLocations(int n){
		try{
			queryingLock.lock();
			numQueryingLocations=n;
		}
		catch(Exception e){}
		finally{
			queryingLock.unlock();
		}
	}
}
