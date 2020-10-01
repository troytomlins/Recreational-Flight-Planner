package seng202.group10.model;

/**
 * Class for defining a Route
 */
public class Route {
    private String airlineCode;
    private String sourceAirportCode;
    private Airport sourceAirport;
    private String destinationAirportCode;
    private Airport destinationAirport;
    private int stops;

    /**
     * First Constructor for the Route Class
     * @param airlineCode Airline code
     * @param sourceAirportCode Start airport code
     * @param sourceAirport Start airport
     * @param destinationAirportCode End airport code
     * @param destinationAirport End airport
     * @param stops Number of stops
     */
    public Route(String airlineCode, String sourceAirportCode, Airport sourceAirport, String destinationAirportCode, Airport destinationAirport, int stops) {
        this.airlineCode = airlineCode;
        this.sourceAirportCode = sourceAirportCode;
        this.sourceAirport = sourceAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.destinationAirport = destinationAirport;
        this.stops = stops;
    }

    /**
     * Second Constructor for the Route Class
     * @param airlineCode Airline code
     * @param sourceAirportCode Start airport code
     * @param destinationAirportCode End airport code
     * @param stops Number of stops
     */
    public Route(String airlineCode, String sourceAirportCode, String destinationAirportCode, int stops) {
        this.airlineCode = airlineCode;
        this.sourceAirportCode = sourceAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.stops = stops;
    }

    /**
     * Is the other route the same as this?
     * Checks the values, not reference
     * @return other == this
     */
    public boolean equals(Route other) {
        return airlineCode.equals(other.airlineCode) &&
                sourceAirportCode.equals(other.sourceAirportCode) &&
                sourceAirport.equals(other.sourceAirport) &&
                destinationAirport.equals(other.destinationAirport) &&
                destinationAirportCode.equals(other.destinationAirportCode) &&
                stops == other.stops;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public int getStops() {
        return stops;
    }
}
