package com.example.dublintravel;

public class Theme {

	private final static int LIGHT_THEME = R.style.LightTheme;
	private final static int DARK_THEME = R.style.DarkTheme;
	private static int current_theme = DARK_THEME;
	
	public static void changeTheme(){
		if(current_theme == LIGHT_THEME){
			current_theme = DARK_THEME;
		}
		else{
			current_theme = LIGHT_THEME;
		}
	}
	
	public static int getCurrentTheme(){
		return current_theme;
	}
	
	public static boolean isDarkTheme(){
		return current_theme == DARK_THEME;
	}
	
}
