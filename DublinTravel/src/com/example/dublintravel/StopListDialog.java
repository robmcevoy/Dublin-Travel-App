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
	private ArrayList<Stop> toDisplay;
	ArrayList<Stop> favourites;
	private Button allStopsBtn;
	private Button favouritesBtn;
	private LinearLayout tabBar;
	private FavouritesDatabase favouritesDb;
	private boolean favouritesTabActive;
	private final String errorMessage = "Unable to make favourite operation";
	private StopAdapter stopAdapter;
	
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
		GetStopsThread thread = new GetStopsThread(this, rtpiController.getCurrentOperator());
		thread.execute();
		setOnItemClickListener();
		setSearchBarListener();
		setTabClickListners();
	}
	
	public void updateStops(ArrayList<Stop> stops) {
		this.allStops = stops;
		this.toDisplay = stops;
		stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1, toDisplay);
		listview.setAdapter(stopAdapter);
		displayStops();
	}
	
	private void displayStops(){
		favouritesDb.open();
		ArrayList<Stop> favouritedStops = favouritesDb.getFavourites(rtpiController.getCurrentOperator());
		boolean favourited;
		for(Stop stop: toDisplay){
			favourited = false;
			for(Stop favouritedStop: favouritedStops){
				if(stop.equals(favouritedStop)){
					stop.favourite();
					favourited = true;
				}
			}
			if(!favourited){
				stop.unfavourite();
			}
		}
		favouritesDb.close();
		stopAdapter = new StopAdapter(rtpiController, android.R.layout.simple_list_item_1, toDisplay);
		listview.setAdapter(stopAdapter);
	}
	
	private void activateTab(){
		Button toActivate;
		Button toDeactivate;
		Drawable img;
		if(!favouritesTabActive){
			toActivate = allStopsBtn;
			toDeactivate = favouritesBtn;
			img = rtpiController.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important);
		}
		else{
			toActivate = favouritesBtn;
			toDeactivate = allStopsBtn;
			img = rtpiController.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important_active);
		}
		toActivate.setTextColor(rtpiController.getCurrentContext().getResources().getColor(R.color.orange));
		toDeactivate.setTextColor(rtpiController.getCurrentContext().getResources().getColor(R.color.light_grey));
		img.setBounds( 0, 0, 60, 60 );
		favouritesBtn.setCompoundDrawables( img, null, null, null );
		wipeSearch();
	}
	
	public synchronized void makeElementsVisible(){
		searchBar.setVisibility(View.VISIBLE);
		tabBar.setVisibility(View.VISIBLE);
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
	
	private void wipeSearch(){
		searchBar.setText("");
		searchBar.setHint(R.string.search);
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
				Stop stop = (Stop) listview.getAdapter().getItem(position);
				int lastViewedPosition = listview.getFirstVisiblePosition();
				View v = listview.getChildAt(0);
				int topOffset = (v == null) ? 0 : v.getTop();	
				try{
					favouritesDb.open();
					if(!stop.isFavourite()){
						favouritesDb.addFavourite(stop, rtpiController.getCurrentOperator());
					}
					else{
						favouritesDb.deleteFavorite(stop, rtpiController.getCurrentOperator());
					}
					favouritesDb.close();
					displayStops();
					listview.setSelectionFromTop(lastViewedPosition, topOffset);
				}
				catch(Exception e){
					Toast.makeText(rtpiController.getCurrentContext(), errorMessage,Toast.LENGTH_LONG).show();
				}
				return true;
			}
		});
	}
	
	private void setTabClickListners(){
		allStopsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	favouritesTabActive = false;
            	activateTab();
            	toDisplay = allStops;
            	displayStops();
            }
        });
		
		favouritesBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	favouritesTabActive = true;
            	activateTab();
            	favouritesDb.open();
        		ArrayList<Stop> favouritedStops = favouritesDb.getFavourites(rtpiController.getCurrentOperator());
        		favouritesDb.close();
        		toDisplay = favouritedStops;
        		displayStops();
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
			ArrayList<Stop> toSearch = allStops;
			for(Stop tempStop: toSearch){
				if(tempStop.getName().toLowerCase().contains(search.toLowerCase()) ||
						tempStop.getID().toLowerCase().contains(search.toLowerCase())){
						subset.add(tempStop);
				}
			}
			toDisplay = subset;
			displayStops();
		}});
	}
}
