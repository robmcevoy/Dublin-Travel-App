/* creates twitter widget based on information provided by Android Javascript interface
 * calls to functions that begin with Android eg: Android.getOperator()
 * are calls to the Android Javscript Interface
 */

window.onload = function()
{	
	const DUBLIN_BUS_WIDGET_1 = '<a class="twitter-timeline" href="https://twitter.com/dublinbusnews" data-widget-id="568519125048520704" data-chrome="transparent"';
	const DUBLIN_BUS_WIDGET_2 = '>Tweets by @dublinbusnews</a>';
	const IRISH_RAIL_WIDGET_1 = '<a class="twitter-timeline" href="https://twitter.com/IrishRail" data-widget-id="568740339671109632" data-chrome="transparent"';
	const IRISH_RAIL_WIDGET_2 = '>Tweets by @IrishRail</a>';
	const LUAS_WIDGET_1 = '<a class="twitter-timeline" href="https://twitter.com/Luas" data-widget-id="568757850940649472" data-chrome="transparent"';
	const LUAS_WIDGET_2 = '>Tweets by @Luas</a>';
	const BUS_EIREANN_WIDGET_1 = '<a class="twitter-timeline" href="https://twitter.com/Buseireann" data-widget-id="568758429033177088" data-chrome="transparent"';
	const BUS_EIREANN_WIDGET_2 = '>Tweets by @Buseireann</a>';
	const AA_ROADWATCH_WIDGET_1 = '<a class="twitter-timeline" href="https://twitter.com/aaroadwatch" data-widget-id="582232237401358336" data-chrome="transparent"';
	const AA_ROADWATCH_WIDGET_2 = '>Tweets by @aaroadwatch</a>';
	const DARK = 'data-theme="dark"';
	const DIV = "operator";
	var active_widget;
	
	if(Android.getOperator() === Android.getDublinBusOpCode()){
		active_widget = DUBLIN_BUS_WIDGET_1;
		if(Android.isDarkTheme()){
			active_widget = active_widget + DARK;
		}
		active_widget = active_widget + DUBLIN_BUS_WIDGET_2;
	}

	else if(Android.getOperator() === Android.getIrishRailOpCode()){
		active_widget= IRISH_RAIL_WIDGET_1;
		if(Android.isDarkTheme()){
			active_widget = active_widget + DARK;
		}
		active_widget = active_widget + IRISH_RAIL_WIDGET_2;
	}

	else if(Android.getOperator() === Android.getLuasOpCode()){
		active_widget= LUAS_WIDGET_1;
		if(Android.isDarkTheme()){
			active_widget = active_widget + DARK;
		}
		active_widget = active_widget + LUAS_WIDGET_2;
	}
	else if(Android.getOperator() === Android.getBusEireannOpCode()){
		active_widget= BUS_EIREANN_WIDGET_1;
		if(Android.isDarkTheme()){
			active_widget = active_widget + DARK;
		}
		active_widget = active_widget + BUS_EIREANN_WIDGET_2;
	}

	else{
		active_widget= AA_ROADWATCH_WIDGET_1;
		if(Android.isDarkTheme()){
			active_widget = active_widget + DARK;
		}
		active_widget = active_widget + AA_ROADWATCH_WIDGET_2;
	}
	console.log(active_widget);
	
	/* opcode appended to html body for testing purpose */
	document.getElementById(DIV).className = Android.getOperator();
	var para = document.createElement("P");
    var t = document.createTextNode(Android.getOperator());
    para.appendChild(t);
    document.getElementById(DIV).appendChild(para);
	document.body.innerHTML =  active_widget + document.body.innerHTML;
	twttr.widgets.load();
}
