var labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var labelIndex = 0;

var map;


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


function addMarker(location) {
    sendLatLngToJava(location);
    var marker = new google.maps.Marker({
        position: location,
        label: labels[labelIndex++ % labels.length],
        map: map
    });
}


function resizeMap() {
    let mapElement = document.querySelector("#googleMap");
    mapElement.style.height = window.innerHeight.toString() + "px";
}
resizeMap();
window.onresize = resizeMap;


function sendLatLngToJava(latLng) {
    javaConnector.newLatLng(latLng.lat(), latLng.lng());
}

//var jsConnector = {
//    showResult: function (result) {
//        document.getElementById('result').innerHTML = result;
//    }
//};

function getJsConnector() {
    return jsConnector;
}
