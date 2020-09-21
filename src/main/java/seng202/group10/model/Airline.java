package seng202.group10.model;

/**
 * Class for defining an Airline.
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

    /**
     * Constructor for the Airline Class.
     * @param name Name of Airline.
     * @param alias Airline alias.
     * @param iata iata code.
     * @param icao icao code.
     * @param callsign Airline call sign.
     * @param country Country Airline based in.
     */
    public Airline(String name, String alias, String iata, String icao, String callsign, String country) {
        this.name = name;
        this.alias = alias;
        this.iata = iata;
        this.icao = icao;
        this.callsign = callsign;
        this.country = country;
    }

    /**
     * Checks if this Airline has the same values as 'other' Airline.
     * @param other Other Airline for checking.
     * @return Boolean value for if they are the same or not
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

    /**
     * Converts the Airline into a string.
     * @return Airline details in string format.
     */
    public String toString() {
        return String.format("Airline(%s, %s, %s, %s, %s, %s)", name, alias, iata, icao, callsign, country);
    }
}
