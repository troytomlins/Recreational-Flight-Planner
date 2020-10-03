package seng202.group10.model;

/**
 * @author Tom Rizzi
 * @author Niko Tainui
 */
public class Aircraft {
    // means travel agent code
    private String iata;
    // name of plane
    private String name;
    // icao stands for aircraft type code
    private String icao;
    // range of aircraft
    private double range;

    /**
     * Constructor for Aircraft class.
     * @param iata Travel agent code.
     * @param name Name of plane.
     * @param icao Aircraft type code.
     * @param range Aircraft's range.
     */
    public Aircraft(String iata, String name, String icao, double range) {
        this.iata = iata;
        this.name = name;
        this.icao = icao;
        this.range = range;
    }

    public String getIata() {
        return iata;
    }

    public String getName() {
        return name;
    }

    public String getIcao() {
        return icao;
    }

    public double getRange() {
        return range;
    }

    /**
     * Checks if this Aircraft has the same values as 'other' Aircraft.
     * @param other Other Aircraft for checking values.
     * @return Boolean value to say if they are equal.
     */
    public Boolean sameValues(Aircraft other) {
        return iata.equals(other.getIata()) &&
                name.equals(other.getName()) &&
                icao.equals(other.getIcao()) &&
                range == (other.getRange());
    }

    public String toString() {
        return String.format("Name: %s, Range: %fkm",name,range);
    }
}
