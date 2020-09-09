package seng202.group10.model;

/**
 * @Author Zach
 */

public class FlightLeg {
    private String id;
    private String type;
    private double latitude;
    private double longitude;
    private double altitude;
    private double legDistance;

    public FlightLeg(String id, String type, double latitude, double longitude, double altitude, double legDistance) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.legDistance = legDistance;
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

    public double getLegDistance() {
        return legDistance;
    }

    public void setLegDistance(double distance) {
        legDistance = distance;
    }


}
