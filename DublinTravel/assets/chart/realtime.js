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

var chart;
var chartData = [];
var firstCall = true;

function generateChartData() {
	console.log("generate data method called");
	var count = Android.getStopInfoCount();
	console.log("count " +  count);
	for(var i=0; i<count; i++){
		var dueDate = stringParser(Android.getDueDate(i));
		var differenceTmp = Android.getDifference(i);
		console.log("  due " + dueDate);
		console.log("  difference" + differenceTmp);
		chartData.push({
			due: dueDate,
			difference: differenceTmp,
			customBullet: active_image
		});
	}
	console.log("exited loop");
	if(!firstCall){
		chart.zoomToIndexes(0, 5);
	}
	else{
		firstCall = false;
	}
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

AmCharts.ready(function() {
	generateChartData();
	chart = new AmCharts.AmSerialChart();
	chart.dataProvider = chartData;
	
	// VALUE AXIS
	var valueAxis = new AmCharts.ValueAxis();
	valueAxis.dashLength = 4;
    valueAxis.position = "left";
    valueAxis.axisColor = "#FF9900";
    valueAxis.axisThickness = 3;
    valueAxis.gridColor = "#FF9900" ; 
    valueAxis.color = "#FF9900";
    valueAxis.title = "Difference to Schedule (mins)";
    valueAxis.titleColor = "#FF9900";
    chart.addValueAxis(valueAxis);
    
    chart.pathToImages = "http://www.amcharts.com/lib/images/";
    
    // GRAPH
    var graph = new AmCharts.AmGraph();
    graph.bulletSize = 50;
    graph.customBulletField = "customBullet";
    graph.valueField = "difference";
    graph.lineColor = "#69BF00";
    graph.negativeLineColor= "#FF0303";
    graph.lineThickness = 3;
    chart.addGraph(graph);
    
    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();
    chartScrollbar.backgroundColor = "#333333";
    chartScrollbar.selectedBackgroundColor = "#FF9900";
    chart.addChartScrollbar(chartScrollbar);

    chart.categoryField = "due";
    
    // CATEGORY AXIS
    var categoryAxis = chart.categoryAxis;
    categoryAxis.axisColor = "#FF9900";
    categoryAxis.axisThickness = 3;
    categoryAxis.gridColor = "#FF9900";
    categoryAxis.color = "#FF9900";
    categoryAxis.title = "Due (mins)";
    categoryAxis.titleColor = "#FF9900";
    categoryAxis.parseDates = true;
    categoryAxis.minPeriod = "mm";

    // WRITE
    chart.write("chartdiv");
    
    chart.zoomToIndexes(0, 5);
    
    setInterval(function () {
    	console.log("update called");
    	chartData = [];
    	generateChartData();
    	chart.validateData();
    }, 1000);

});