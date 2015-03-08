var BACKGROUND_COLOR= Android.getBackgroundColor();
var PRIMARY_ROAD_COLOR="#A8A8A8"; 
var SECONDARY_ROAD_AND_LABEL_COLOR =  "#FAFAFA";  
var LIGHTER_SECONDARY = "#E8E8E8";
const STYLE_TITLE = "Dark Theme";

function getMapStyle(){
	return [
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
	            ]
;}