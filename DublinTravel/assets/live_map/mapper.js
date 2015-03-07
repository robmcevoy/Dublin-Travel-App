//getLocations();
var map;
const ZOOM = 1;
const STREET_VIEW_CONTROL = false;
const DIV = 'map-canvas';
const dublin_bus_image = "../img/map_dublin_bus_icon.gif";
const irish_rail_image = "../img/map_irish_rail_icon.gif";
const bus_eireann_image = "../img/map_bus_eireann_icon.gif";
const luas_image="../img/map_luas_icon.gif";
const STYLE_TITLE = "Dark Theme";
var BACKGROUND_COLOR= Android.getBackgroundColor();
var PRIMARY_ROAD_COLOR="#A8A8A8"; 
var SECONDARY_ROAD_AND_LABEL_COLOR =  "#FAFAFA";  
var LIGHTER_SECONDARY = "#E8E8E8";
var SIZE_WIDTH=60;
var SIZE_HEIGHT=50;
var tmpLocation1 = new google.maps.LatLng(53.392295, -6.382072);
var tmpLocation2 = new google.maps.LatLng(53.377535, -6.395137);
var tmpLocation3 = new google.maps.LatLng(53.379977, -6.355617);
var tmpLocation4 = new google.maps.LatLng(53.373052, -6.362634);
var dublinBusIcon = {
		  url: dublin_bus_image,
		  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
};

var irishRailIcon = {
		  url: irish_rail_image,
		  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
};

var busEireannIcon = {
		  url: bus_eireann_image,
		  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
};

var luasIcon = {
		  url: luas_image,
		  size: new google.maps.Size(SIZE_WIDTH, SIZE_WIDTH),
		  origin: new google.maps.Point(0, 0),
		  anchor: new google.maps.Point(17, 34),
		  scaledSize: new google.maps.Size(SIZE_WIDTH, SIZE_HEIGHT)
};

function initialize() {
	var styles = [
	                {
	                    "featureType": "all",
	                    "elementType": "labels.text.fill",
	                    "stylers": [
	                        {
	                            "saturation": 36
	                        },
	                        {
	                            "color": PRIMARY_ROAD_COLOR
	                        },
	                        {
	                            "lightness": 40
	                        }
	                    ]
	                },
	                {
	                    "featureType": "all",
	                    "elementType": "labels.text.stroke",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        },
	                        {
	                            "color": BACKGROUND_COLOR
	                        },
	                        {
	                            "lightness": 16
	                        }
	                    ]
	                },
	                {
	                    "featureType": "all",
	                    "elementType": "labels.icon",
	                    "stylers": [
	                        {
	                            "visibility": "off"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "administrative",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        },
	                        {
	                            "lightness": 20
	                        }
	                    ]
	                },
	                {
	                    "featureType": "administrative",
	                    "elementType": "geometry.stroke",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        },
	                        {
	                            "lightness": 17
	                        },
	                        {
	                            "weight": 1.2
	                        }
	                    ]
	                },
	                {
	                    "featureType": "administrative.locality",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "administrative.neighborhood",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "administrative.land_parcel",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "simplified"
	                        },
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        },
	                        {
	                            "lightness": 20
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.man_made",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.man_made",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.landcover",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.landcover",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.landcover",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.terrain",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "off"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.terrain",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "landscape.natural.terrain",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "poi",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "lightness": 21
	                        },
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "poi",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "poi",
	                    "elementType": "geometry.stroke",
	                    "stylers": [
	                        {
	                            "color": BACKGROUND_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.highway",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        },
	                        {
	                            "lightness": 17
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.highway",
	                    "elementType": "geometry.stroke",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        },
	                        {
	                            "lightness": 29
	                        },
	                        {
	                            "weight": 0.2
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.highway.controlled_access",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        },
	                        {
	                            "color": PRIMARY_ROAD_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.arterial",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": PRIMARY_ROAD_COLOR
	                        },
	                        {
	                            "lightness": 18
	                        },
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.arterial",
	                    "elementType": "geometry.stroke",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.local",
	                    "elementType": "all",
	                    "stylers": [
	                        {
	                            "visibility": "off"
	                        },
	                        {
	                            "color": PRIMARY_ROAD_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.local",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": PRIMARY_ROAD_COLOR
	                        },
	                        {
	                            "lightness": 16
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.local",
	                    "elementType": "geometry.fill",
	                    "stylers": [
	                        {
	                            "visibility": "on"
	                        }
	                    ]
	                },
	                {
	                    "featureType": "road.local",
	                    "elementType": "geometry.stroke",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        }
	                    ]
	                },
	                {
	                    "featureType": "transit",
	                    "elementType": "geometry",
	                    "stylers": [
	                        {
	                            "color": SECONDARY_ROAD_AND_LABEL_COLOR
	                        },
	                        {
	                            "lightness": 19
	                        }
	                    ]
	                }
	            ];

	 var styledMap = new google.maps.StyledMapType(styles,
	    {name: STYLE_TITLE});
	  
	var mapOptions = {
			zoom: ZOOM,
			streetViewControl: STREET_VIEW_CONTROL,
			mapTypeControlOptions: {
			      mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'map_style']
			}
	};
	map = new google.maps.Map(document.getElementById(DIV),mapOptions);
	/*
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
	*/
	map.mapTypes.set('map_style', styledMap);
	map.setMapTypeId('map_style')
	  
	
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
	if(Android.hasCurrentLocation()){
		var newLatLng = new google.maps.LatLng(Android.getCurrentLatitude(), Android.getCurrentLongitude());
		var currentMarker = new google.maps.Marker({
			map: map,
			position: newLatLng
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasDublinBusLocation()){
		var newLatLng = new google.maps.LatLng(Android.getDublinBusLatitude(), Android.getDublinBusLongitude());
		var dublinBusStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasIrishRailLocation()){
		var newLatLng = new google.maps.LatLng(Android.getIrishRailLatitude(), Android.getIrishRailLongitude());
		var irishRailStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasLuasLocation()){
		var newLatLng = new google.maps.LatLng(Android.getLuasLatitude(), Android.getLuasLongitude());
		var luasStopMarker = new google.maps.Marker({
			map: map,
			position: newLatLng
		});
		latlngbounds.extend(newLatLng);
	}
	if(Android.hasBusEireannLocation()){
		var newLatLng = new google.maps.LatLng(Android.getBusEireannLatitude(), Android.getBusEireannLongitude());
		var busEireannMarker = new google.maps.Marker({
			map: map,
			position: newLatLng
		});
		latlngbounds.extend(newLatLng);
	}
	map.fitBounds(latlngbounds);
}

function constantLoop() {
	console.log("here");
	if(Android.isQuerying()){
		getLocations();
	}
};