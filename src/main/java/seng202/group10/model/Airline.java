package seng202.group10.model;

/**
 * Class to represent an airline
 * @author Troy Tomlins
 * @author Zac Kaye
 */

public class Airline {
    private String name;
    private String alias;
    private String iata;
    private String icao;
    private String callsign;
    private String country;

    public Airline(String name, String alias, String iata, String icao, String callsign, String country) {
        this.name = name;
        this.alias = alias;
        this.iata = iata;
        this.icao = icao;
        this.callsign = callsign;
        this.country = country;
    }

    /**
     * Does this and other have the same values?
     * @param other Airline to check equalness
     * @return whether this and other have the same values
     */
    public boolean equals(Airline other) {
        return name.equals(other.name) &&
                alias.equals(other.alias) &&
                iata.equals(other.iata) &&
                icao.equals(other.icao) &&
                callsign.equals(other.callsign) &&
                country.equals(other.country);
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public String getCallsign() {
        return callsign;
    }

    public String getCountry() {
        return country;
    }

    public String toString() {
        return String.format("Airline(%s, %s, %s, %s, %s, %s)", name, alias, iata, icao, callsign, country);
    }
}
