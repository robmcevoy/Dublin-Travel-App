/* creates visualization of transport operators traveling towards a stop
 * calls to functions that begin with Android eg: Android.getBackgroundColor()
 * are calls to the Android Javscript Interface
 */

const IRISH_RAIL_IMAGE = "../img/irish_rail.png";
const DUBLIN_BUS_IMAGE = "../img/dublin_bus.png";
const BUS_EIREANN_IMAGE = "../img/bus_eireann.png";
const LUAS_IMAGE="../img/luas.png";
const LUAS_DARK_IMAGE="../img/luas_dark.png";
const DB_MARKER="../img/dublin_bus_marker.png";
const IR_MARKER="../img/irish_rail_marker.png";
const BE_MARKER="../img/bus_eireann_marker.png";
const LUAS_MARKER="../img/luas_marker.png";
var active_image;
var marker;
const CAT_TITLE = "Due Time";
const VALUE_TITLE = "Difference to Schedule (mins)";
const GUIDE_TITLE = "On Time";
const BACKGROUND_COLOR = Android.getBackgroundColor();
const SECONDARY_COLOR = Android.getSecondaryColor();
const GREEN_COLOR = "#69BF00";
const RED_COLOR = "#FF0303";
const DASH_LENGTH = 4;
const BULLET_SIZE = 50;
const LINE_THICKNESS = 3;
const AXIS_THICKNESS = 3;
const MIN_PERIOD = "mm";
var chartData = [];
const IRISH_RAIL_PARSE = [0,4,5,7,8,10,11,13,14,16,17,19];
const RTPI_PARSE = [6,10,3,5,0,2,11,13,14,16,17,19];

window.onload = function () {
	document.body.style.backgroundColor = BACKGROUND_COLOR;
}

if(Android.getOperator() === Android.getDublinBusOpCode()){
	active_image= DUBLIN_BUS_IMAGE;
	marker = DB_MARKER;
}

else if(Android.getOperator() === Android.getIrishRailOpCode()){
	active_image= IRISH_RAIL_IMAGE;
	marker = IR_MARKER;
}

else if(Android.getOperator() === Android.getLuasOpCode()){
	active_image= LUAS_IMAGE;
	if(Android.isDarkTheme()){
		active_image= LUAS_DARK_IMAGE;
	}
	marker = LUAS_MARKER;
}

else{
	active_image= BUS_EIREANN_IMAGE;
	marker = BE_MARKER;
}

function getData(){
	var serverTime = Android.getServerTime();
	if(active_image != IRISH_RAIL_IMAGE){
		serverTime = rtpiStringParser(serverTime);
	}else if(active_image == IRISH_RAIL_IMAGE){
		serverTime = irishRailStringParser(serverTime);
	}
	chartData.push({
		due: serverTime,
		difference: "0",
		customBullet: marker
	});
	for(var i=0; (i<Android.getStopInfoCount() && i<Android.getMaxOnChart()); i++){
		var dueDate = rtpiStringParser(Android.getDueDate(i));
		if(dueDate < serverTime){
			dueDate = serverTime;
		}
		chartData.push({
			due: dueDate,
			difference: Android.getDifference(i),
			customBullet: active_image
		});
	}	
	return chartData;
}

function rtpiStringParser(date_string){
	// format of input 20/02/2015 15:20:12
	return parseDate(date_string, RTPI_PARSE);
}

function irishRailStringParser(date_string){
	// format of input 2015-03-30T13:47:37.53
	return parseDate(date_string, IRISH_RAIL_PARSE);
}

function parseDate(date_string, parseArray){
	var year = date_string.substring(parseArray[0],parseArray[1]);
	var month = date_string.substring(parseArray[2],parseArray[3]);
	var monthInt = parseInt(month);
	monthInt = monthInt -1;
	month = monthInt.toString();
	var day = date_string.substring(parseArray[4],parseArray[5]);
	var hour = date_string.substring(parseArray[6],parseArray[7]);
	var min = date_string.substring(parseArray[8],parseArray[9]);
	var sec = date_string.substring(parseArray[10],parseArray[11]);
	return new Date(year, month, day, hour, min, sec);
}

var chart = AmCharts.makeChart("chartdiv", {
    "type": "serial", 
    "dataProvider": getData(),
    "valueAxes": [{
        "position": "left",
        "axisColor": SECONDARY_COLOR,
        "axisThickness" : AXIS_THICKNESS,
        "gridColor" : SECONDARY_COLOR,
        "color" : SECONDARY_COLOR,
        "title": VALUE_TITLE,
        "titleColor": SECONDARY_COLOR
    }],
    "pathToImages": "http://www.amcharts.com/lib/3/images/",
    "graphs": [{
        "bulletSize": BULLET_SIZE,
        "customBulletField": "customBullet",
        "valueField": "difference",
        "lineColor": GREEN_COLOR,
        "negativeLineColor": RED_COLOR,
        "lineThickness": LINE_THICKNESS
    }],
    "categoryField": "due",
    "categoryAxis": {
        "axisColor": SECONDARY_COLOR,
        "axisThickness" : AXIS_THICKNESS,
        "gridColor" : SECONDARY_COLOR,
        "color" : SECONDARY_COLOR,
        "title": CAT_TITLE,
        "titleColor": SECONDARY_COLOR,
        "parseDates": true,
        "minPeriod": MIN_PERIOD
    }
});