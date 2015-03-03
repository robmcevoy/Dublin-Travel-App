const irish_rail_image = "../img/irish_rail.png";
const dublin_bus_image = "../img/dublin_bus.png";
const bus_eireann_image = "../img/bus_eireann.png";
const pole_image = "../img/pole.png"
const luas_image="../img/luas.png";
const DUBLIN_BUS_OPCODE = "bac";
const IRISH_RAIL_OPCODE = "ir";
const LUAS_OPCODE = "luas";
var active_image;
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

window.onload = function () {
	document.body.style.backgroundColor = BACKGROUND_COLOR;
}

if(Android.getOperator() === DUBLIN_BUS_OPCODE){
	active_image= dublin_bus_image;
}

else if(Android.getOperator() === IRISH_RAIL_OPCODE){
	active_image= irish_rail_image;
}

else if(Android.getOperator() === LUAS_OPCODE){
	active_image= luas_image;
}

else{

	active_image= bus_eireann_image;
}

function getData(){
	var chartData = [];
	var serverTime = Android.getServerTime();
	if(active_image != irish_rail_image){
		serverTime = stringParser(serverTime)
	}
	chartData.push({
		due: serverTime,
		difference: "0",
		customBullet: pole_image
	});
	for(var i=0; (i<Android.getStopInfoCount() && i<Android.getMaxOnChart()); i++){
		chartData.push({
			due: stringParser(Android.getDueDate(i)),
			difference: Android.getDifference(i),
			customBullet: active_image
		});
	}
	return chartData;
}

function stringParser(date_string){
	// format of input "20/02/2015 15:20:12"
	
	var year = date_string.substring(6,10);
	var month = date_string.substring(3,5);
	var monthInt = parseInt(month);
	monthInt = monthInt -1;
	month = monthInt.toString();
	var day = date_string.substring(0,2);
	var hour = date_string.substring(11,13);
	var min = date_string.substring(14,16);
	var sec = date_string.substring(17,19);
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