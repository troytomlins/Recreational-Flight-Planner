package seng202.group10.model;

import java.util.ArrayList;

public class Route {
    private String airlineCode;
    private int airlineId;
    private String sourceAirportCode;
    private String sourceAirport;
    private String destinationAirportCode;
    private String destinationAirport;
    private int stops;
    private ArrayList<String> equipment;

    public Route(String airlineCode, int airlineId, String sourceAirportCode, String sourceAirport, String destinationAirportCode, String destinationAirport, int stops, ArrayList<String> equipment) {
        this.airlineCode = airlineCode;
        this.airlineId = airlineId;
        this.sourceAirportCode = sourceAirportCode;
        this.sourceAirport = sourceAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.destinationAirport = destinationAirport;
        this.stops = stops;
        this.equipment = equipment;
    }

    public Route(String airlineCode, String sourceAirportCode, String destinationAirportCode, int stops, ArrayList<String> equipment) {
        this.airlineCode = airlineCode;
        this.sourceAirportCode = sourceAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.stops = stops;
        this.equipment = equipment;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public int getStops() {
        return stops;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }
}
