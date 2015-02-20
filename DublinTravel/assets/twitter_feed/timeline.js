window.onload = function()
{	
	const DUBLIN_BUS_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/dublinbusnews" data-widget-id="568519125048520704">Tweets by @dublinbusnews</a>';
	const IRISH_RAIL_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/IrishRail" data-widget-id="568740339671109632">Tweets by @IrishRail</a>';
	const LUAS_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/Luas" data-widget-id="568757850940649472">Tweets by @Luas</a>';
	const BUS_EIREANN_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/Buseireann" data-widget-id="568758429033177088">Tweets by @Buseireann</a>';
	const DUBLIN_BUS_OPCODE = "bac";
	const IRISH_RAIL_OPCODE = "ir";
	const LUAS_OPCODE = "luas";
	var active_widget;
	
	if(Android.getOperator() === DUBLIN_BUS_OPCODE){
		active_widget= DUBLIN_BUS_WIDGET;
	}

	else if(Android.getOperator() === IRISH_RAIL_OPCODE){
		active_widget= IRISH_RAIL_WIDGET;
	}

	else if(Android.getOperator() === LUAS_OPCODE){
		active_widget= LUAS_WIDGET;
	}

	else{
		active_widget= BUS_EIREANN_WIDGET;
	}
	document.body.innerHTML = active_widget;
	twttr.widgets.load()
}
