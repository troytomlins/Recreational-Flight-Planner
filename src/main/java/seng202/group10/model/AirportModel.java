package seng202.group10.model;

import java.util.ArrayList;

public class AirportModel {

    private ArrayList<Airport> airports;

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    /**
     * addAirports adds an Airport object to an ArrayList of Airport objects.
     * If the Airport object is already in the ArrayList, the Airport object is not added.
     * @param airport an Airport object.
     */
    public void addAirport(Airport airport) {
        if (!airports.contains(airport)) {
            airports.add(airport);
        }
    }

}
