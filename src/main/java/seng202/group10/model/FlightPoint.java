package seng202.group10.model;

/**
 * Flight point is a point the flight passes through, each leg of the flight has two FlightPoints
 * @author Zach Kaye
 */
public class FlightPoint {
    public String id;
    public String type;
    public double latitude;
    public double longitude;
    public double altitude;

    /**
     * Constructor for Flight Point.
     * @param type Type
     * @param id Flight Point ID
     * @param altitude Altitude value
     * @param lat Latitude value
     * @param lng Longitude value
     */
    public FlightPoint(String type, String id, double altitude, double lat, double lng) {
        this.id = id;
        this.type = type;
        this.latitude = lat;
        this.longitude = lng;
        this.altitude = altitude;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

}
