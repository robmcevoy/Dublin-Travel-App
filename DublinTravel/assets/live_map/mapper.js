const ZOOM = 13;
const STREET_VIEW_CONTROL = false;
const DIV = 'map-canvas';
const dublin_bus_image = "../img/dublin_bus.png";
const irish_rail_image = "../img/irish_rail.png";
const dublin_bus_image = "../img/dublin_bus.png";
const bus_eireann_image = "../img/bus_eireann.png";
const pole_image = "../img/pole.png"
const luas_image="../img/luas.png";
var tmpLocation1 = new google.maps.LatLng(53.392295, -6.382072);
var tmpLocation2 = new google.maps.LatLng(53.377535, -6.395137);
var tmpLocation3 = new google.maps.LatLng(53.379977, -6.355617);
var tmpLocation4 = new google.maps.LatLng(53.373052, -6.362634);
var dublinBusIcon = {
		  url: dublin_bus_image,
		  size: new google.maps.Size(70, 50),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(30, 20)
};

var irishRailIcon = {
		  url: irish_rail_image,
		  size: new google.maps.Size(70, 50),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(30, 20)
};

var busEireannIcon = {
		  url: bus_eireann_image,
		  size: new google.maps.Size(70, 50),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(30, 20)
};

var luasIcon = {
		  url: luas_image,
		  size: new google.maps.Size(70, 50),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(30, 20)
};

function initialize() {
	var longitude = Android.getLongitude();
	var latitude = Android.getLatitude();
	var currentLocation = new google.maps.LatLng(latitude, longitude);
	var mapOptions = {
			center: currentLocation,
			zoom: ZOOM,
			streetViewControl: STREET_VIEW_CONTROL
	};
	var map = new google.maps.Map(document.getElementById(DIV),mapOptions);
	var currentLocationMarker = new google.maps.Marker({
	      position: currentLocation,
	      map: map
	});
	var dublinBusMarker = new google.maps.Marker({
		position: tmpLocation1,
		map: map,
		icon: dublinBusIcon
	});
	
	var irishRailMarker = new google.maps.Marker({
		position: tmpLocation2,
		map: map,
		icon: irishRailIcon
	});
	
	var busEireannMarker = new google.maps.Marker({
		position: tmpLocation3,
		map: map,
		icon: busEireannIcon
	});
	
	var luasMarker = new google.maps.Marker({
		position: tmpLocation4,
		map: map,
		icon: luasIcon
	});
	var trafficLayer = new google.maps.TrafficLayer();
	trafficLayer.setMap(map);
 }
google.maps.event.addDomListener(window, 'load', initialize);