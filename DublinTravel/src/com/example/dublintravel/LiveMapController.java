package com.example.dublintravel;

import android.content.Context;
import android.widget.ImageView;

public class LiveMapController extends Controller{
	
	private LiveMapNavigationBar navbar;
	Operator[] operators;
	
	public LiveMapController(Context context, LiveMapNavigationBar navbar){
		super(context);
		this.navbar = navbar;
		this.navbar.activate(this);
		operators = new Operator[navbar.getNumOperators()];
	}

	public void changeActiveOperator(Operator operator, ImageView imageView) {
		changeImageViewBorder(imageView);
	}

}
