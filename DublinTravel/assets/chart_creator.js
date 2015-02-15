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
var chart = AmCharts.makeChart("chartdiv", {
    "type": "serial",    
    "dataProvider": [{
        "due": 1,
        "value": 1,
        "customBullet": active_image
    }, {
        "due": 3,
        "value": 3,
        "customBullet": active_image
    }, {
        "due": 8,
        "value": 8,
        "customBullet": active_image
    }, {
        "due": 10,
        "value": 4,
        "customBullet": active_image
    }, {
        "due": 15,
        "value": 0,
        "customBullet": active_image
    }, {
        "due": 18,
        "value": -3,
        "customBullet": active_image
    }, {
        "due": 23,
        "value": -5,
        "customBullet": active_image
    }, {
        "due": 25,
        "value": -7,
        "customBullet": active_image
    }, {
        "due": 27,
        "value": -10,
        "customBullet": active_image
    }, {
        "due": 28,
        "value": -3,
        "customBullet": active_image
    }, {
        "due": 33,
        "value": -1,
        "customBullet": active_image
    }, {
        "due": 40,
        "value": 0,
        "customBullet": active_image
    }],
    "valueAxes": [{
        "dashLength": 4,
        "position": "left",
        "axisColor": "#FF9900",
        "axisThickness" : 3,
        "gridColor" : "#FF9900",
        "color" : "#FF9900"
    }],
    "graphs": [{
        "bulletSize": 50,
        "customBulletField": "customBullet",
        "valueField": "value",
        "lineColor": "#69BF00",
        "negativeLineColor": "#FF0303",
        "lineThickness": 3,
    }],
    "chartCursor": {graphBulletSize:1.5},
    "autoMargins": true,
    "categoryField": "due",
    "categoryAxis": {
        "axisColor": "#FF9900",
        "axisThickness" : 3,
        "gridColor" : "#FF9900",
        "color" : "#FF9900"
    }
});