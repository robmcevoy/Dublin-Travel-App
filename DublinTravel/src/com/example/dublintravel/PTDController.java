package com.example.dublintravel;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PTDController extends Controller {
	
	private Stop currentStop;
	private Operator operator;
	private TextView stopView;
	private ListView stopInfoListView;
	private ChartWebView chartVis;
	private TwitterFeed twitterFeed;
	private boolean firstSelection;
	private boolean queryStarted;
	private Queryer queryer;
	private PTDNavigationBar navbar;
	
	PTDController(Activity activity){		
		super(activity);
		this.activity = activity;
		this.stopView = (TextView) activity.findViewById(R.id.stop);
		this.stopInfoListView = (ListView) activity.findViewById(R.id.stopInfoListView);
		this.chartVis = new ChartWebView((WebView)activity.findViewById(R.id.webView1),this);
		this.twitterFeed = new TwitterFeed((WebView) activity.findViewById(R.id.twitterFeed), this);
		this.chartVis.start();
		this.twitterFeed.start();
		this.firstSelection = true;
		this.queryStarted = false;
		this.queryer = new Queryer(this);
		setStopListener();
		this.navbar = new PTDNavigationBar(activity);
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
		changeImageViewBorder(imageView);
		chartVis.reload();
		twitterFeed.reload();
		if(queryStarted){
			queryer.interrupt();
		}
		if(this.operator.hasPreviousStop()){
			this.currentStop = this.operator.getPreviousStop();
			changeStop(this.operator.getPreviousStop());
		}
		else
			this.currentStop = null;
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
	
	private void setStopListener(){
		final PTDController tmp  = this; 
		stopView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	StopListDialog stopListDialog = new StopListDialog( tmp);
            	stopListDialog.open();
            }
        });
	}
	
	public synchronized String getServerTime(){
		ArrayList<StopInfo> array = getStopInfos();
		if(array != null){
			return array.get(0).getServerTime();
		}
		else{
			return "";
		}
	}
	
	public NavigationBar getNavBar(){
		return this.navbar;
	}
}
