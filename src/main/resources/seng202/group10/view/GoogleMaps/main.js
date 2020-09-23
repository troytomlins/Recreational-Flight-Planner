var labels = ["Start", "A", "B", "C", "more lol"]
var labelIndex = 0;

var map;
var javaConnector;      // Placeholder
var markers = [];



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
 * Make a new marker
 * tell java about it with sendLocationToJava
 * add it to the markers dict
 */
function addMarker(location) {
    // TODO give more than 26 markers
    let currentIndex = labelIndex
    var label = labels[labelIndex++];
    marker = newMarker(label, location)

    google.maps.event.addListener(marker, 'click', function(event) {
        removeMarker(currentIndex);
    });

    markers[currentIndex] = marker
    sendLocationToJava(label, location);
}


/**
 * Create (and return) a new google maps marker
 * adds it to the map as well
 */
function newMarker(label, location) {
    var marker = new google.maps.Marker({
        position: location,
        label: label,
        map: map
    });
    return marker
}


/**
 * Remove the marker with label
 */
function removeMarker(label) {
    labelIndex -= 1;
    markers[label].setMap(null);    // Take off map
    markers[label] = null;          // Forget about it all together
    relabelMarkers();
    // TODO Tell java
}


// Set marker labels so they are in order and none are missing
function relabelMarkers() {
    // Remove non existent markers
    for (let i in markers) {
        let marker = markers[i];
        if (marker == null) {
            markers.splice(i, 1);
        }
    }

    // Relabel markers
    for (let i in markers) {
        let marker = markers[i];
        let newLabel = labels[i];
        marker.label = newLabel;
        marker.setLabel(newLabel);
    }
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
function sendLocationToJava(id, latLng) {
    if (javaConnector) {
        javaConnector.newLatLng(id, latLng.lat(), latLng.lng());
    } else {
        console.log("No Java connector found! Are you running this in the app?")
    }
}


