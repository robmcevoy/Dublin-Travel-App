package com.example.dublintravel;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
	private PTDController controller;
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
	private final double WIDTH_PERCENTAGE_PORTRAIT = 0.9;
	private final double HEIGHT_PERCENTAGE_PORTRAIT = 0.7;
	private final double WIDTH_PERCENTAGE_LANDSCAPE = 0.5;
	private final double HEIGHT_PERCENTAGE_LANDSCAPE = 0.9;
	private final double TAB_ICON_SIZE_MULTIPLIER= 0.7;
	private final int ORIENTATION_LANDSCAPE = 2;
	private WindowManager.LayoutParams lp;
	
	public StopListDialog(PTDController controller){
		this.controller = controller;
		dialog = new Dialog(controller.getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog);
		setDialogSize();
		listview = (ListView) dialog.findViewById(R.id.stopsListView);
		searchBar = (EditText) dialog.findViewById(R.id.searchBar);
		progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
		tabBar = (LinearLayout) dialog.findViewById(R.id.tabBar);
		allStopsBtn = (Button) dialog.findViewById(R.id.allStopsBtn);
		favouritesBtn = (Button) dialog.findViewById(R.id.favouritesBtn);
		favouritesTabActive = false;
		favourites = new ArrayList<Stop>();
		allStops = new ArrayList<Stop>();
		favouritesDb = new FavouritesDatabase(controller.getCurrentContext());
		favouritesDb.open();
	}
	
	private void setDialogSize(){
		Point size = new Point();
		WindowManager wm = (WindowManager) controller.getActivity().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getSize(size);
		lp = new WindowManager.LayoutParams();
	    lp.copyFrom(dialog.getWindow().getAttributes());
	    int orientation = controller.getCurrentContext().getResources().getConfiguration().orientation;
	    double x,y;
	    if(orientation == ORIENTATION_LANDSCAPE){
	    	x = WIDTH_PERCENTAGE_LANDSCAPE;
	    	y = HEIGHT_PERCENTAGE_LANDSCAPE;
	    }
	    else{
	    	x = WIDTH_PERCENTAGE_PORTRAIT;
	    	y = HEIGHT_PERCENTAGE_PORTRAIT;
	    }
	    lp.width = (int) (size.x * x);
	    lp.height = (int) (size.y * y);
	}
	
	public void open(){
		dialog.show();
		dialog.getWindow().setAttributes(lp);
		activateTab();
		GetStopsThread thread = new GetStopsThread(this, controller.getCurrentOperator());
		thread.execute();
		setOnItemClickListener();
		setSearchBarListener();
		setTabClickListners();
	}
	
	public void updateStops(ArrayList<Stop> stops) {
		this.allStops = stops;
		this.toDisplay = stops;
		stopAdapter = new StopAdapter(controller, android.R.layout.simple_list_item_1, toDisplay);
		listview.setAdapter(stopAdapter);
		displayStops();
	}
	
	private void displayStops(){
		favouritesDb.open();
		ArrayList<Stop> favouritedStops = favouritesDb.getFavourites(controller.getCurrentOperator());
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
		stopAdapter = new StopAdapter(controller, android.R.layout.simple_list_item_1, toDisplay);
		listview.setAdapter(stopAdapter);
	}
	
	private void activateTab(){
		Button toActivate;
		Button toDeactivate;
		Drawable img;
		if(!favouritesTabActive){
			toActivate = allStopsBtn;
			toDeactivate = favouritesBtn;
			img = controller.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important);
		}
		else{
			toActivate = favouritesBtn;
			toDeactivate = allStopsBtn;
			img = controller.getCurrentContext().getResources().getDrawable(R.drawable.ic_action_important_active);
		}
		toActivate.setTextColor(controller.getCurrentContext().getResources().getColor(R.color.orange));
		toDeactivate.setTextColor(controller.getCurrentContext().getResources().getColor(R.color.light_grey));
		img.setBounds( 0, 0, (int)(img.getIntrinsicWidth()*TAB_ICON_SIZE_MULTIPLIER), (int)(img.getIntrinsicWidth()*TAB_ICON_SIZE_MULTIPLIER) );
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
				controller.changeStop(stop);
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
						favouritesDb.addFavourite(stop, controller.getCurrentOperator());
					}
					else{
						favouritesDb.deleteFavorite(stop, controller.getCurrentOperator());
					}
					favouritesDb.close();
					displayStops();
					listview.setSelectionFromTop(lastViewedPosition, topOffset);
				}
				catch(Exception e){
					Toast.makeText(controller.getCurrentContext(), errorMessage,Toast.LENGTH_LONG).show();
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
        		ArrayList<Stop> favouritedStops = favouritesDb.getFavourites(controller.getCurrentOperator());
        		favouritesDb.close();
        		ArrayList<Stop> newToDisplay = new ArrayList<Stop>();
        		for(Stop favourited: favouritedStops){
        			for(Stop stop: allStops){
        				if(favourited.equals(stop)){
        					newToDisplay.add(stop);
        				}
        			}
        		}
        		toDisplay = newToDisplay;
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
