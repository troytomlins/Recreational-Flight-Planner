package seng202.group10.model;

import java.util.ArrayList;

/**
 * Class that facilitates the searching of airports
 * @author Mitchell Freeman
 */
public class AirportFinder {
    private AirportRW airportRW;
    private ArrayList<Airport> airports;

    /**
     * Constructor for AirportFinder.
     * Creates a new RW and reads Airports from it and saves to ArrayList.
     */
    public AirportFinder() {
        airportRW = new AirportRW();
        try {
            airports = airportRW.readAirports();
        } catch(Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * Finds Airport in ArrayList airports using the airport's code.
     * @param airportCode Airport code
     * @return Found Airport or null if not found
     */
    public Airport findAirport(String airportCode) {
        Airport outputAirport = null;
        for (Airport airport: airports) {
            if (airport.getIata().equals(airportCode) || airport.getIcao().equals(airportCode)) {
                outputAirport = airport;
            }
        }
        return outputAirport;
    }
}
