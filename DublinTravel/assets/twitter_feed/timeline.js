window.onload = function()
{	
	const DUBLIN_BUS_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/dublinbusnews" data-widget-id="568519125048520704">Tweets by @dublinbusnews</a>';
	const IRISH_RAIL_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/IrishRail" data-widget-id="568740339671109632">Tweets by @IrishRail</a>';
	const LUAS_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/Luas" data-widget-id="568757850940649472">Tweets by @Luas</a>';
	const BUS_EIREANN_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/Buseireann" data-widget-id="568758429033177088">Tweets by @Buseireann</a>';
	const AA_ROADWATCH_WIDGET = '<a class="twitter-timeline" href="https://twitter.com/aaroadwatch" data-widget-id="582232237401358336">Tweets by @aaroadwatch</a>';
	const DUBLIN_BUS_OPCODE = "bac";
	const IRISH_RAIL_OPCODE = "ir";
	const LUAS_OPCODE = "luas";
	const BUS_EIREANN_OPCODE = "be";
	const DIV = "operator";
	var active_widget;
	
	if(Android.getOperator() === Android.getDublinBusOpCode()){
		active_widget= DUBLIN_BUS_WIDGET;
	}

	else if(Android.getOperator() === Android.getIrishRailOpCode()){
		active_widget= IRISH_RAIL_WIDGET;
	}

	else if(Android.getOperator() === Android.getLuasOpCode()){
		active_widget= LUAS_WIDGET;
	}
	else if(Android.getOperator() === Android.getBusEireannOpCode()){
		active_widget= BUS_EIREANN_WIDGET;
	}

	else{
		active_widget= AA_ROADWATCH_WIDGET;
	}
	
	document.getElementById(DIV).className = Android.getOperator();
	var para = document.createElement("P");
    var t = document.createTextNode(Android.getOperator());
    para.appendChild(t);
    document.getElementById(DIV).appendChild(para);
	document.body.innerHTML =  active_widget + document.body.innerHTML;
	twttr.widgets.load();
}
