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
     * Build the flight point
     */
    public FlightPoint(String type, String id, double altitude, double lat, double lng) {
        this.id = id;
        this.type = type;
        this.latitude = lat;
        this.longitude = lng;
        this.altitude = altitude;
    }
}
