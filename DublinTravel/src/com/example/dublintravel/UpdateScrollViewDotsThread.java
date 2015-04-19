package com.example.dublintravel;

import android.app.Activity;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class UpdateScrollViewDotsThread extends AsyncTask<Rect, Void, String>{

	private ImageView dot1;
	private ImageView dot2;
	private ImageView dot3;
	private View v1;
	private View v2;
	private View v3;
	private Rect scrollBounds;
	
	public UpdateScrollViewDotsThread(Activity activity, View v1, View v2, View v3){
		dot1 = (ImageView) activity.findViewById(R.id.dot1);
		dot2 = (ImageView) activity.findViewById(R.id.dot2);
		dot3 = (ImageView) activity.findViewById(R.id.dot3);
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	protected String doInBackground(Rect... arg0) {
		scrollBounds = arg0[0];
		return "";
	}
	
	protected void onPostExecute(String result){
		int d1 = R.drawable.dot;
		int d2 = R.drawable.dot;
		int d3 = R.drawable.dot;
		if(v1.getLocalVisibleRect(scrollBounds)){
			d1 = R.drawable.dot_active;
		}
		else if(v2.getLocalVisibleRect(scrollBounds)){
			d2 = R.drawable.dot_active;
		}
		else{
			d3 = R.drawable.dot_active;
		}
		dot1.setImageResource(d1);
		dot2.setImageResource(d2);
		dot3.setImageResource(d3);
	}

}
