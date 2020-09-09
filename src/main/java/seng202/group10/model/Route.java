package seng202.group10.model;

import java.util.ArrayList;

public class Route {
    private String airlineCode;
    private int airlineId;
    private String sourceAirportCode;
    private Airport sourceAirport;
    private String destinationAirportCode;
    private Airport destinationAirport;
    private int stops;
    private ArrayList<String> equipment;

    public Route(String airlineCode, String sourceAirportCode, Airport sourceAirport, String destinationAirportCode, Airport destinationAirport, int stops) {
        this.airlineCode = airlineCode;
        this.sourceAirportCode = sourceAirportCode;
        this.sourceAirport = sourceAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.destinationAirport = destinationAirport;
        this.stops = stops;
    }

    public Route(String airlineCode, String sourceAirportCode, String destinationAirportCode, int stops) {
        this.airlineCode = airlineCode;
        this.sourceAirportCode = sourceAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.stops = stops;
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
