var irish_rail_image = "img/irish_rail.png";
var dublin_bus_image = "img/dublin_bus.png";
var bus_eireann_image = "img/bus_eireann.png";
var luas_image="img/luas.png";
const DUBLIN_BUS_OPCODE = "bac";
const IRISH_RAIL_OPCODE = "ir";
const LUAS_OPCODE = "luas";
var active_image;


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
	for(var i=0; i<Android.getStopInfoCount(); i++){
		chartData.push({
			due: Android.getDueTime(i),
			difference: Android.getDifference(i),
			customBullet: active_image
		});
	}
	return chartData;
}

var chart = AmCharts.makeChart("chartdiv", {
    "type": "serial", 
    "dataProvider": getData(),
    "valueAxes": [{
        "dashLength": 4,
        "position": "left",
        "axisColor": "#FF9900",
        "axisThickness" : 3,
        "gridColor" : "#FF9900",
        "color" : "#FF9900",
        "title": "Differeence to Schedule (mins)",
        "titleColor": "#FF9900"
    }],
    "pathToImages": "http://www.amcharts.com/lib/3/images/",
    "graphs": [{
        "bulletSize": 50,
        "customBulletField": "customBullet",
        "valueField": "difference",
        "lineColor": "#69BF00",
        "negativeLineColor": "#FF0303",
        "lineThickness": 3,
    }],

    "chartScrollbar": {
        "autoGridCount": "true",
        "backgroundColor": "#333333",
        "selectedBackgroundColor": "#FF9900"
    },
    "categoryField": "due",
    "categoryAxis": {
        "axisColor": "#FF9900",
        "axisThickness" : 3,
        "gridColor" : "#FF9900",
        "color" : "#FF9900",
        "title": "Due (mins)",
        "titleColor": "#FF9900"
    }
});
chart.addListener("rendered", zoom);
zoom();

function zoom(){
	//only maximum of 5
	chart.zoomToIndexes(0, 5);
}