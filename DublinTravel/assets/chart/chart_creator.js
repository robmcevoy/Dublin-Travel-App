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
	console.log("input: " + date_string);
	console.log("year: " + year);
	console.log("month: " + month);
	console.log("day: " + day);
	console.log("hour: " + hour);
	console.log("min: " + min);
	console.log("sec: " + sec);
	return new Date(year, month, day, hour, min, sec);
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
    "categoryField": "due",
    "categoryAxis": {
        "axisColor": "#FF9900",
        "axisThickness" : 3,
        "gridColor" : "#FF9900",
        "color" : "#FF9900",
        "title": "Due (mins)",
        "titleColor": "#FF9900",
        "parseDates": true,
        "minPeriod": "mm"
    }
});
chart.addListener("rendered", zoom);
zoom();

function zoom(){
	//only maximum of 5
	chart.zoomToIndexes(0, 5);
}