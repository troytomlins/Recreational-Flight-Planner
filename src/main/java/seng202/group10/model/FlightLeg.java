package seng202.group10.model;

public class FlightLeg {
    private String id;
    private String type;
    private double latitude;
    private double longitude;
    private float altitude;
    private double legDistance;

    public FlightLeg(String id, String type, double latitude, double longitude, float altitude, double legDistance) {
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

    public float getAltitude() {
        return altitude;
    }

    public double getLegDistance() {
        return legDistance;
    }

    public void setLegDistance() {
        double latitude2 = 0;
        double longitude2 = 0;
        legDistance = calculateLegDistance(latitude, latitude2, longitude, longitude2);
    }

    public double calculateLegDistance(double latitude1, double latitude2, double longitude1, double longitude2) {
        double distance = 0;

        return distance;
    }
    // Distance between two points.
    // Fuel Efficency.

}
