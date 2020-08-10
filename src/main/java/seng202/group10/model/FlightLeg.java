package seng202.group10.model;

public class FlightLeg {
    private String id;
    private String type;
    private float latitude;
    private float longitude;
    private float altitude;
    private float legDistance;
    private float totalDistance;

    public FlightLeg(String id, String type, float latitude, float longitude, float altitude, float legDistance, float totalDistance) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.legDistance = legDistance;
        this.totalDistance = totalDistance;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public float getLegDistance() {
        return legDistance;
    }

    public float getTotalDistance() {
        return totalDistance;
    }
}
