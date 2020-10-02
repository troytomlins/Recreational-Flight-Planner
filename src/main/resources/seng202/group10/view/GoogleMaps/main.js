var labels = ["Start", "A", "B", "C", "D", "E", "F", "aaaaaaaaaaaa"]
var labelIndex = 0;

var map;
var javaConnector; // Placeholder
var markers = [];


class LabelHandler {
    constructor() {
        this.labelIndex = 0;
        this.firstLabel = "0";
//        this.endLabel = "end";
    }

    /**
     * Get a list of labels
     * @param {int} numLabels how many labels to get
     */
    getLabels(numLabels) {
        this.labelIndex = 0;
    }

    /**
     * Get the next label
     * @returns {[string, int]} the label and the index
     */
    getNextLabel() {
        let label;
        if (this.labelIndex == 0) {
            label = this.firstLabel;
        } else {
            // let letterLabels = Math.max(0, this.labelIndex - 2);
//            label = this.makeLetterLabel(this.labelIndex - 1);
            label = this.labelIndex.toString();
        }
        return [label, this.labelIndex++];
    }

    /**
     * n = 0; return a
     * n = 1; return b
     * n = 26; return aa
     * n = 27; return ab
     * etc
     * @param {int} n 
     */
    makeLetterLabel(n) {
        let s = "";

        s += String.fromCharCode(97 + n % 26);
        if (n > 26) {
            s += String.fromCharCode(97 + Math.floor(n / 26) - 1);
        }
        return s;
    }
}


class MyMarker {

    constructor(label, labelIndex, latLng) {
        this.label = label;
        this.labelIndex = labelIndex;

        javaConnector.newMarker(label, latLng.lat(), latLng.lng());

        this.mapsMarker = new google.maps.Marker({
            position: latLng,
            label: this.label,
            map: map,
            draggable: true
        });

        let self = this;
        google.maps.event.addListener(this.mapsMarker, 'click', function(event) {
            removeMarker(self);
        });

        google.maps.event.addListener(this.mapsMarker, 'drag', function(event) {
            javaConnector.moveMarker(self.label, self.mapsMarker.position.lat(), self.mapsMarker.position.lng());
        });
    }

    getPosition() {
        return this.mapsMarker.getPosition();
    }

    delete() {
        this.mapsMarker.setMap(null);
    }

    setLabel(newLabel, newLabelIndex) {
        this.label = newLabel;
        this.labelIndex = newLabelIndex;
        this.mapsMarker.setLabel(this.label);
    }
}


let labelHandler = new LabelHandler();


/**
 * Initialize the map
 * Add the listener for adding markers
 */
function initMap() {
    var ucPos = { lat: -43.522456, lng: 172.579422 };
    map = new google.maps.Map(document.getElementById('googleMap'), {
        center: new google.maps.LatLng(ucPos.lat, ucPos.lng),
        zoom: 15,
        disableDefaultUI: true,
    });
    google.maps.event.addListener(map, 'click', function(event) {
        addMarker(event.latLng);
    });
}


function newMarker(lat, lng) {
//    addMarker(new google.maps.LatLng(lat, lng));
    addMarker(new google.maps.LatLng(lat, lng));
}


/**
 * Make a new marker
 * tell java about it with sendLocationToJava
 * add it to the markers dict
 */
function addMarker(location) {
    // TODO give more than 26 markers
    let currentIndex = labelHandler.labelIndex;
    // var label = labels[labelIndex++];
    let [label, labelIndex] = labelHandler.getNextLabel();
    let marker = new MyMarker(label, labelIndex, location);
    markers[currentIndex] = marker
}



function removeMarker(marker) {
    javaConnector.removeMarker(marker.label);
    // Remove from map
    marker.delete();
    // Remove from markers array
    markers.splice(marker.labelIndex, 1);
    // Reset marker labels
    labelHandler.labelIndex = 0;
    for (let i = 0; i < markers.length; i++) {
        // Change labels
        let [newLabel, newIndex] = labelHandler.getNextLabel()
        markers[i].setLabel(newLabel, newIndex);
    }
    labelIndex -= 1;
}


function removeAllMarkers() {
    for (let marker of markers) {
        marker.delete();
    }
    labelHandler.labelIndex = 0;
    markers = [];
}


// Get a list of labels, lats and lngs to pass to java
function makeJavaMarkerLists() {
    let labels = [];
    let lats = [];
    let lngs = [];
    for (let marker of markers) {
        labels.push(marker.label);
        lats.push(marker.getPosition().lat());
        lngs.push(marker.getPosition().lng());
    }
    return [labels, lats, lngs];
}


/**
 * Print some text into the java console
 */
function println(text) {
    javaConnector.println(text);
}


/**
 * Control vertical map resizing
 * Can't size to 100% in css so this how we gotta do it
 */
function resizeMap() {
    let mapElement = document.querySelector("#googleMap");
    mapElement.style.height = window.innerHeight.toString() + "px";
}

resizeMap(); // Initial size
window.onresize = resizeMap; // Listener
