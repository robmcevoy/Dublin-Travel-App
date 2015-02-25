package com.example.dublintravel;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
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
	private boolean firstSelection;
	private boolean queryStarted;
	private Queryer queryer;
	private NavigationBar navbar;

	RtpiController(Context context, NavigationBar navbar, TextView stopView, ListView stopInfoListView, WebView chartVis, WebView twitterWebview){		
		this.context = context;
		this.stopView = stopView;
		this.navbar = navbar;
		this.stopInfoListView = stopInfoListView;
		this.chartVis = new ChartWebView(chartVis,this);
		this.twitterFeed = new TwitterFeed(twitterWebview, this);
		this.chartVis.start();
		this.twitterFeed.start();
		this.firstSelection = true;
		this.queryStarted = false;
		this.queryer = new Queryer(this);
		setStopListener();
		this.navbar.activate(this);
	}
	
	public synchronized void changeStop(Stop newStop){
		currentStop = newStop;
		operator.setPreviousStop(currentStop);
		setStopView();
		chartVis.reload();
		if(firstSelection){
			queryer.start();
			firstSelection = false;
			queryStarted = true;
		}
		else{
			queryer.interrupt();
		}
	}
	
	public synchronized void changeActiveOperator(Operator operator, ImageView imageView){
		wipeStopView();
		wipeStopInfoView();
		this.operator = operator;
		if(this.operator.hasPreviousStop()){
			this.currentStop = this.operator.getPreviousStop();
			setStopView();
		}
		else
			this.currentStop = null;
		changeImageViewBorder(imageView);
		chartVis.reload();
		twitterFeed.reload();
		if(queryStarted){
			queryer.interrupt();
		}
	}
	
	private void changeImageViewBorder(ImageView imageView){
		if(activeImageView != null){
			activeImageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_dark_grey));
		}
		imageView.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner_orange));
		activeImageView = imageView;
	}
	
	public synchronized Operator getCurrentOperator(){
		return operator;
	}
	
	public synchronized Stop getCurrentStop(){
		return currentStop;
	}
	
	public synchronized ChartWebView getChartWebView(){
		return chartVis;
	}
	
	public synchronized ListView getStopInfoListView(){
		return stopInfoListView;
	}
	
	private void wipeStopView(){
		stopView.setText(context.getResources().getString(R.string.stop_id_hint));
	}
	
	private synchronized void wipeStopInfoView(){
		stopInfoListView.setAdapter(null);
	}
	
	private void setStopView(){
		stopView.setText(currentStop.getName());
	}
	
	public synchronized ArrayList<StopInfo> getStopInfos(){
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
	
	public synchronized Context getCurrentContext(){
		return context;
	}
	
	private void setStopListener(){
		final RtpiController tmp  = this; 
		stopView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	StopListDialog stopListDialog = new StopListDialog( tmp);
            	stopListDialog.open();
            }
        });
	}	
}
