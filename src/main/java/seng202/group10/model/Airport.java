package seng202.group10.model;

/**
 * Class for defining an Airport.
 * @author Troy Tomlins
 */
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

    /**
     * Constructor for the Airport Class.
     * @param name Name of the Airport.
     * @param city City Airport is located in.
     * @param country Country Airport is located in.
     * @param iata Airports iata code.
     * @param icao Airports icao code.
     * @param latitude Airports latitude value.
     * @param longitude Airports longitude value.
     * @param altitude Airports altitude.
     * @param timezone Timezone of Airport.
     * @param dstType dstType.
     * @param tzDatabase tzDatabase.
     */
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

    @Override
    public String toString(){
        return getName();
    }

    /**
     * Returns true if all values of other airport match this airport
     * @param airport Airport to compare
     * @return bool if all values match
     */
    public Boolean sameValues(Airport airport) {
        return (this.getName().equals(airport.getName()) &&
                this.getCity().equals(airport.getCity()) &&
                this.getCountry().equals(airport.getCountry()) &&
                this.getIata().equals(airport.getIata()) &&
                this.getIcao().equals(airport.getIcao()) &&
                this.getDstType().equals(airport.getDstType()) &&
                this.getTzDatabase().equals(airport.getTzDatabase()) &&
                this.getLatitude() == airport.getLatitude() &&
                this.getLongitude() == airport.getLongitude() &&
                this.getAltitude() == airport.getAltitude() &&
                this.getTimezone() == airport.getTimezone());
    }

}
