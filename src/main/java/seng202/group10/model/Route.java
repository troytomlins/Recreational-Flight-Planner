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
}
