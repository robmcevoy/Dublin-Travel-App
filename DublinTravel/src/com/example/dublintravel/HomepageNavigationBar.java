package com.example.dublintravel;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

/* navigation bar for homepage,
 * a click of any option will result in the launching of a new activity
 */

public class HomepageNavigationBar extends NavigationBar {
	
	private ImageView userManualImageView;
	private ImageView themeBtn;
	
	public HomepageNavigationBar(Activity activity){
		super(activity, R.layout.activity_homepage);
		this.userManualImageView = (ImageView) activity.findViewById(R.id.userManual);
		this.themeBtn = (ImageView) activity.findViewById(R.id.themeToggle);
		setUserManualClick();
		setToggleButtonClick();
	}


	@Override
	public void operatorClick(ImageView imageview, final Operator operator) {
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	for(int i=0; i<NUM_OPERATORS; i++){
            		operators[i].deactivate();
            	}
            	operator.activate();
            	Intent i = new Intent(getContext(), PTDActivity.class);
            	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            	operator.deactivate();
            }
        });
		
	}

	@Override
	public void setMapClick(ImageView imageview) {
		imageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
            	Intent i = new Intent(getContext(), MapDashboardActivity.class);
            	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            }
        });
	}
	
	private void setUserManualClick(){
		userManualImageView.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View v)
            {
				Intent i = new Intent(getContext(), UserManualActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	i.putExtras(createBundle());
            	getContext().startActivity(i);
            }
			
        });
	}
	
	private void setToggleButtonClick(){
		themeBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
            {
				Theme.changeTheme();
				activity.recreate();
            }
		});
	}

}
