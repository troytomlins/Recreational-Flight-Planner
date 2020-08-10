package seng202.group10.model;

public class Airport {
    private String name;
    private String city;
    private String iata;
    private String icao;
    private float latitude;
    private float longitude;
    private float altitude;
    private float timezone;
    private char dstType;
    private String tzDatabase;

    public Airport(String name, String city, String iata, String icao, float latitude, float longitude, float altitude, float timezone, char dstType, String tzDatabase) {
        this.name = name;
        this.city = city;
        this.iata = iata;
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timezone = timezone;
        this.dstType = dstType;
        this.tzDatabase = tzDatabase;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
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

    public float getTimezone() {
        return timezone;
    }

    public char getDstType() {
        return dstType;
    }

    public String getTzDatabase() {
        return tzDatabase;
    }

}
