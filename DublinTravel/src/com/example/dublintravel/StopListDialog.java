package com.example.dublintravel;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

public class StopListDialog {
	
	private Dialog dialog;
	private Context context;
	private ListView listview;
	private EditText searchBar;
	private ProgressBar progressBar;
	private ArrayList<Stop> stopsArray;
	private StopAdapter adapter;
	private RtpiController rtpiController;
	private boolean firstSearch;
	private ArrayList<Stop> allStops;

	StopListDialog(Context context, RtpiController rtpiController){
		this.context = context;
		this.rtpiController = rtpiController;
		dialog = new Dialog(this.context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog);
		listview = (ListView) dialog.findViewById(R.id.stopsListView);
		searchBar = (EditText) dialog.findViewById(R.id.searchBar);
		progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
		stopsArray = new ArrayList<Stop>();
		adapter = new StopAdapter(context, android.R.layout.simple_list_item_1, stopsArray);
		firstSearch = true;
		allStops = new ArrayList<Stop>();
	}
	
	public void open(){
		listview.setAdapter(adapter);
		dialog.show();
		GetStopsThread thread = new GetStopsThread(context,rtpiController.getCurrentOperator(), adapter, progressBar);
		thread.execute(listview);
		setOnItemClickListener();
		setSearchBarListener();
	}
	
	private void setOnItemClickListener(){
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				Stop stop = adapter.getItem(position);
				rtpiController.changeStop(stop);
				dialog.dismiss();
			}
        });
	}
	
	private void setSearchBarListener(){
		searchBar.addTextChangedListener(new TextWatcher(){

		public void afterTextChanged(Editable arg0) {}

		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

		public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			if(firstSearch){
				
				for(int i=0; i<adapter.getCount(); i++ ){
					allStops.add(adapter.getItem(i));
				}
				firstSearch = false;
				
			}
			String search = s+"";
			ArrayList<Stop> subset = new ArrayList<Stop>();

			for(Stop tempStop: allStops){
				if(tempStop.getName().toLowerCase().contains(search.toLowerCase()) ||
						tempStop.getID().toLowerCase().contains(search.toLowerCase())){
						subset.add(tempStop);
				}
			}
			adapter.clear();
			for(Stop tempStop: subset){
				adapter.add(tempStop);
			}
			
		}});
	}
}
