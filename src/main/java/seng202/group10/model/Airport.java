package seng202.group10.model;

public class Airport {
    private String name;
    private String city;
    private String country;
    private String iata;
    private String icao;
    private double latitude;
    private double longitude;
    private float altitude;
    private float timezone;
    private String dstType;
    private String tzDatabase;

    public Airport(String name, String city, String country, String iata, String icao, double latitude, double longitude, float altitude, float timezone, String dstType, String tzDatabase) {
        this.name = name;
        this.city = city;
        this.country = country;
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

    public String getCountry() { return country; }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
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

    public float getTimezone() {
        return timezone;
    }

    public String getDstType() {
        return dstType;
    }

    public String getTzDatabase() {
        return tzDatabase;
    }

}
