function myMap() {

    var mapProp= {
      center:new google.maps.LatLng(-43.522456, 172.579422),
      zoom:15,
    };

    var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
}

function resizeMap() {
    let mapElement = document.querySelector("#googleMap");
    mapElement.style.height = window.innerHeight.toString() + "px";
}

resizeMap();
window.onresize = resizeMap;