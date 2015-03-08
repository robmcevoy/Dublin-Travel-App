var map;
var currentPosition;
var currentLocationKnown =false;
const ZOOM = 1;
const STREET_VIEW_CONTROL = false;
const DIV = 'map-canvas';

function initialize() {
	  
	var mapOptions = {
			zoom: ZOOM,
			streetViewControl: STREET_VIEW_CONTROL,
			mapTypeControlOptions: {
			      mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'map_style']
			}
	};
	
	map = new google.maps.Map(document.getElementById(DIV),mapOptions);  
	
	var trafficLayer = new google.maps.TrafficLayer();
	trafficLayer.setMap(map);
	getLocations();
	constantLoop();
 }

google.maps.event.addDomListener(window, 'load', initialize);

function getLocations(){
	console.log("getLocations()");
	Android.updateLocations();
	var latlngbounds = new google.maps.LatLngBounds();
	if(currentLocationKnown){
		latlngbounds.extend(currentPosition);
	}
	else if(navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(function(position) {
	      currentPosition = new google.maps.LatLng(position.coords.latitude,
	                                       position.coords.longitude);
	      var currentPosMarker = new google.maps.Marker({
	        map: map,
	        position: currentPosition,
	        icon: getCurrentLocationIcon()
	      });
	      
	      currentLocationKnown = true;
	      getLocations();

	    }, function() {
	      handleNoGeolocation(true);
	    });
	} 

	if(Android.hasDublinBusLocation()){
		var newLatLng = new google.maps.LatLng(Android.getDublinBusLatitude(), Android.getDublinBusLongitude());
		var dublinBusStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng,
			icon: getDublinBusStopIcon()
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasIrishRailLocation()){
		var newLatLng = new google.maps.LatLng(Android.getIrishRailLatitude(), Android.getIrishRailLongitude());
		var irishRailStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng,
			icon: getIrishRailStopIcon()
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasLuasLocation()){
		var newLatLng = new google.maps.LatLng(Android.getLuasLatitude(), Android.getLuasLongitude());
		var luasStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng,
			icon: getLuasStopIcon()
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasBusEireannLocation()){
		var newLatLng = new google.maps.LatLng(Android.getBusEireannLatitude(), Android.getBusEireannLongitude());
		var busEireannMarker = new google.maps.Marker({
			map: map,
			position: newLatLng,
			icon: getBusEireannStopIcon()
		});
		latlngbounds.extend(newLatLng);
	}
	map.fitBounds(latlngbounds);
}

function constantLoop() {
	console.log("here");
	while(Android.isQuerying()){
		sleep(300);
		getLocations();
	}
}

function sleep(milliseconds) {
	var start = new Date().getTime();
	for (var i = 0; i < 1e7; i++) {
		if ((new Date().getTime() - start) > milliseconds){
			break;
	    }
	 }
}

function handleNoGeolocation(errorFlag) {
	console.log("handleNoGeoLocation()");
	if (errorFlag) {
		var content = 'Error: The Geolocation service failed.';
	} else {
		var content = 'Error: Your browser doesn\'t support geolocation.';
	}
	console.log(content);
}