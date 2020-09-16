package seng202.group10.model;

import java.util.ArrayList;

public class FlightModel {

    private ArrayList<Flight> flights;

    public FlightModel() { this.flights = new ArrayList<Flight>(); }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    /**
     * addFlights adds a Flight object to an ArrayList of Flight objects.
     * If the Flight object is already in the ArrayList, the Flight object is not added.
     * @param flight a Flight object.
     */
    public void addFlight(Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
        }
    }

}
