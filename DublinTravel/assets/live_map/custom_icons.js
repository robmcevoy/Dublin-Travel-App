var SIZE_WIDTH=60;
var SIZE_HEIGHT=50;
const dublin_bus_image = "../img/map_dublin_bus_icon.gif";
const irish_rail_image = "../img/map_irish_rail_icon.gif";
const bus_eireann_image = "../img/map_bus_eireann_icon.gif";
const luas_image="../img/map_luas_icon.gif";
const CUSTOM_ICON_URL = "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|"
const CURRENT_POSITION_ICON_COLOR = "FF9900"
const DUBLIN_BUS_STOP_ICON_COLOR = "FFE534";
const LUAS_STOP_ICON_COLOR = "483092";
const BUS_EIREANN_STOP_ICON_COLOR = "FF2A1A";
const IRISH_RAIL_STOP_ICON_COLOR = "FFFFFF";

function getDublinBusIcon(){
	var dublinBusIcon = {
			  url: dublin_bus_image,
			  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
			  origin: new google.maps.Point(0, 0),
			  anchor: new google.maps.Point(17, 34),
			  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
	};
	return dublinBusIcon;
}

function getIrishRailIcon(){
	var irishRailIcon = {
			  url: irish_rail_image,
			  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
			  origin: new google.maps.Point(0, 0),
			  anchor: new google.maps.Point(17, 34),
			  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
	};
	return irishRailIcon;
}

function getBusEireannIcon(){
	var busEireannIcon = {
			  url: bus_eireann_image,
			  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
			  origin: new google.maps.Point(0, 0),
			  anchor: new google.maps.Point(17, 34),
			  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
	};
	return busEireannIcon;
}

function getLuasIcon(){
	var luasIcon = {
			  url: luas_image,
			  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
			  origin: new google.maps.Point(0, 0),
			  anchor: new google.maps.Point(17, 34),
			  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
	};
	return luasIcon;
}

function getCurrentLocationIcon(){
	var currentLocationIcon = {
			url: CUSTOM_ICON_URL + CURRENT_POSITION_ICON_COLOR
	};
	return currentLocationIcon;
}

function getDublinBusStopIcon(){
	var dublinBusStopIcon  = {
			url: CUSTOM_ICON_URL + DUBLIN_BUS_STOP_ICON_COLOR
	};
	return dublinBusStopIcon;
}

function getLuasStopIcon(){
	var luasStopIcon  = {
			url: CUSTOM_ICON_URL + LUAS_STOP_ICON_COLOR
	};
	return luasStopIcon;
}

function getBusEireannStopIcon(){
	var busEireannStopIcon  = {
			url: CUSTOM_ICON_URL + BUS_EIREANN_STOP_ICON_COLOR
	};
	return busEireannStopIcon;
}

function getIrishRailStopIcon(){
	var irishRailStopIcon  = {
			url: CUSTOM_ICON_URL + IRISH_RAIL_STOP_ICON_COLOR
	};
	return irishRailStopIcon;
}