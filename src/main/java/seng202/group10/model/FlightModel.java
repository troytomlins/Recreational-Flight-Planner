package seng202.group10.model;

import java.util.ArrayList;

/**
 * Model class for Flight.
 */
public class FlightModel {

    private ArrayList<Flight> flights;

    /**
     * Constructor for FlightModel.
     * Creates ArrayList of Flights.
     */
    public FlightModel() { this.flights = new ArrayList<>(); }

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
