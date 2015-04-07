package com.example.dublintravel;

import java.util.ArrayList;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;

public class StopListDialog {
	
	private Dialog dialog;
	private ListView listview;
	private EditText searchBar;
	private ProgressBar progressBar;
	private RtpiController rtpiController;
	private ArrayList<Stop> allStops;
	ArrayList<Stop> favourites;
	private Button allStopsBtn;
	private Button favouritesBtn;
	private LinearLayout tabBar;
	private FavouritesDatabase favouritesDb;
	private boolean favouritesTabActive;
	
	public StopListDialog(RtpiController rtpiController){
		this.rtpiController = rtpiController;
		dialog = new Dialog(rtpiController.getCurrentContext());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog);
		listview = (ListView) dialog.findViewById(R.id.stopsListView);
		searchBar = (EditText) dialog.findViewById(R.id.searchBar);
		progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
		tabBar = (LinearLayout) dialog.findViewById(R.id.tabBar);
		allStopsBtn = (Button) dialog.findViewById(R.id.allStopsBtn);
		favouritesBtn = (Button) dialog.findViewById(R.id.favouritesBtn);
		favouritesTabActive = false;
		favourites = new ArrayList<Stop>();
		allStops = new ArrayList<Stop>();
		favouritesDb = new FavouritesDatabase(rtpiController.getCurrentContext());
		favouritesDb.open();
	}
	
	public void open(){
		dialog.show();
		activateTab();
		GetStopsThread thread = new GetStopsThread(rtpiController, this, rtpiController.getCurrentOperator());
		thread.execute(listview);
		setOnItemClickListener();
		setSearchBarListener();
		setTabClickListners();
	}
	
	private void activateTab(){
		Button toActivate;
		Button toDeactivate;
		Drawable img;
		if(!favouritesTabActive){
			toActivate = allStopsBtn;
			toDeactivate = favouritesBtn;
			img = rtpiController.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important_active);
		}
		else{
			toActivate = favouritesBtn;
			toDeactivate = allStopsBtn;
			img = rtpiController.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important);
		}
		toActivate.setTextColor(rtpiController.getCurrentContext().getResources().getColor(R.color.orange));
		toDeactivate.setTextColor(rtpiController.getCurrentContext().getResources().getColor(R.color.light_grey));
		img.setBounds( 0, 0, 60, 60 );
		favouritesBtn.setCompoundDrawables( img, null, null, null );
	}
	
	public synchronized void makeElementsVisible(){
		searchBar.setVisibility(View.VISIBLE);
		tabBar.setVisibility(View.VISIBLE);
		StopAdapter tmpAdapter = (StopAdapter) listview.getAdapter();
		for(int i=0; i<tmpAdapter.getCount(); i++ ){
			allStops.add(tmpAdapter.getItem(i));
		}
	}
	
	public synchronized void setLoading(){
		progressBar.setVisibility(View.VISIBLE);
	}
	
	public synchronized void setLoadingProgress(Integer... values){
		progressBar.setProgress(values[0]);
	}
	
	public synchronized void setNotLoading(){
		progressBar.setVisibility(View.GONE);
	}
	
	private void setOnItemClickListener(){
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Stop stop = (Stop) listview.getAdapter().getItem(position);
				rtpiController.changeStop(stop);
				dialog.dismiss();
			}
        });
		
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
				if(!favouritesTabActive){
					Stop stop = (Stop) listview.getAdapter().getItem(position);
					try{
						favouritesDb.addFavourite(stop, rtpiController.getCurrentOperator());
						Toast.makeText(rtpiController.getCurrentContext(), "Stop Added To Favourites",Toast.LENGTH_LONG).show();
					}
					catch(Exception e){
						Toast.makeText(rtpiController.getCurrentContext(), e.toString(),Toast.LENGTH_LONG).show();
					}
				}
				return true;
			}
		});
        
	}
	
	private void setTabClickListners(){
		allStopsBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	favouritesTabActive = false;
            	activateTab();
            	StopAdapter stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1, allStops );
    			listview.setAdapter(stopAdapter);          	
            }
        });
		
		favouritesBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	favouritesTabActive = true;
            	activateTab();
            	try{
            		favouritesDb.open();
            		ArrayList<Stop> stops = favouritesDb.getFavourites(rtpiController.getCurrentOperator());
            		favourites.clear();
            		for(Stop stop1: stops){
            			for(Stop stop2 : allStops){
            				if(stop1.equals(stop2)){
            					favourites.add(stop2);
            				}
            			}
            		}
            		StopAdapter stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1,favourites );
        			listview.setAdapter(stopAdapter);
            	}
            	catch(Exception e){
            		Toast.makeText(rtpiController.getCurrentContext(), e.getMessage(),Toast.LENGTH_LONG).show();
            	}
            }
        });
	}
	
	private void setSearchBarListener(){
		searchBar.addTextChangedListener(new TextWatcher(){

		public void afterTextChanged(Editable arg0) {}

		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

		public void onTextChanged(CharSequence s, int start, int before, int count) {

			String search = s+"";
			ArrayList<Stop> subset = new ArrayList<Stop>();
			ArrayList<Stop> toSearch = new ArrayList<Stop>();
			if(!favouritesTabActive){
				toSearch = allStops;
			}
			else{
				toSearch = favourites;
			}
			for(Stop tempStop: toSearch){
				if(tempStop.getName().toLowerCase().contains(search.toLowerCase()) ||
						tempStop.getID().toLowerCase().contains(search.toLowerCase())){
						subset.add(tempStop);
				}
			}
			StopAdapter stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1,subset );
			listview.setAdapter(stopAdapter);	
		}});
	}
}
