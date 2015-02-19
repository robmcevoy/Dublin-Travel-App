package com.example.dublintravel;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RtpiController {
	
	private Context context;
	private Stop currentStop;
	private Operator operator;
	private ImageView activeImageView;
	private TextView stopView;
	private ListView stopInfoListView;
	private ChartWebView chartVis;
	private TwitterFeed twitterFeed;
	
	RtpiController(Context context, TextView stopView, ListView stopInfoListView, WebView chartVis, WebView twitterWebview){		
		this.context = context;
		this.stopView = stopView;
		this.stopInfoListView = stopInfoListView;
		this.chartVis = new ChartWebView(chartVis,this);
		this.twitterFeed = new TwitterFeed(twitterWebview, this);
		this.chartVis.start();
		this.twitterFeed.start();
	}
	
	public void changeStop(Stop newStop){
		currentStop = newStop;
		setStopView();
		GetStopInfoThread si = new GetStopInfoThread(operator, currentStop.getID(), context, chartVis);
		si.execute(stopInfoListView);
	}
	
	public void changeOperator(Operator operator, ImageView imageView){
		wipeStopView();
		wipeStopInfoView();
		this.operator = operator;
		changeImageViewBorder(imageView);
		chartVis.reload();
	}
	
	private void changeImageViewBorder(ImageView imageView){
		if(activeImageView != null){
			activeImageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_dark_grey));
		}
		imageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_orange));
		activeImageView = imageView;
	}
	
	public Operator getCurrentOperator(){
		return operator;
	}
	
	private void wipeStopView(){
		stopView.setText(context.getResources().getString(R.string.stop_id_hint));
	}
	
	private void wipeStopInfoView(){
		stopInfoListView.setAdapter(null);
	}
	
	private void setStopView(){
		stopView.setText(currentStop.toString());
	}
	
	public ArrayList<StopInfo> getStopInfos(){
		StopInfoAdapter tmpAdapter = (StopInfoAdapter) stopInfoListView.getAdapter();
		if(tmpAdapter != null){
			ArrayList<StopInfo> array = new ArrayList<StopInfo>();
			for(int i=0; i<tmpAdapter.getCount(); i++){
				array.add(tmpAdapter.getItem(i));
			}
			return array;
		}
		else
			return null;
	}
	
}
