/**
 * Handles the google maps
 * When initMap is called, the maps api is added to the window
 * The map takes the the full space given to the page
 */
var labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var labelIndex = 0;

var map;


/**
 * Initialize the map
 * Add the listener for adding markers
 */
function initMap() {
    var ucPos = {lat: -43.522456, lng: 172.579422};
    map = new google.maps.Map(document.getElementById('googleMap'), {
        center:new google.maps.LatLng(ucPos.lat, ucPos.lng),
        zoom:15,
    });
    google.maps.event.addListener(map, 'click', function(event) {
        addMarker(event.latLng);
    });
}


/**
 * Add a marker onto the map at location
 * call the sendLatLngToJava function
 */
function addMarker(location) {
    var markerLabel = labels[labelIndex++ % labels.length];
    sendLatLngToJava(markerLabel, location);
    var marker = new google.maps.Marker({
        position: location,
        label: markerLabel,
        map: map
    });
}


/**
 * Control vertical map resizing
 * Can't size to 100% in css so this how we gotta do it
 */
function resizeMap() {
    let mapElement = document.querySelector("#googleMap");
    mapElement.style.height = window.innerHeight.toString() + "px";
}
resizeMap();                    // Initial size
window.onresize = resizeMap;    // Listener


/**
 * Send a marker to java with id at latLng
 */
function sendLatLngToJava(id, latLng) {
    javaConnector.newLatLng(id, latLng.lat(), latLng.lng());
}

