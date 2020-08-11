package seng202.group10.model;

import java.util.ArrayList;

public class AirlineModel {

    private ArrayList<Airline> airlines;

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    /**
     * addAirlines adds an Airline object to an ArrayList of Airline objects.
     * If the Airline object is already in the ArrayList, the Airline object is not added.
     * @param airline an Airline object.
     */
    public void addAirline(Airline airline) {
        if (!airlines.contains(airline)) {
            airlines.add(airline);
        }
    }

}
