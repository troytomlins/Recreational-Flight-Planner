package seng202.group10.model;

/**
 * Flight point is a point the flight passes through, each leg of the flight has two FlightPoints
 * @author Zach Kaye
 */

public class FlightPoint {
    private String id;
    private String type;
    private double latitude;
    private double longitude;
    private double altitude;

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
