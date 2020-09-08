package seng202.group10.model;

import java.util.ArrayList;

public class AirportFinder {
    private AirportRW airportRW;

    private ArrayList<Airport> airports;

    public AirportFinder() {
        airportRW = new AirportRW();
        airports = airportRW.readAirports();
    }

    public Airport findAirport(String airportCode) {
        Airport outputAirport = null;
        for (Airport airport: airports) {
            if (airport.getIata() == airportCode || airport.getIcao() == airportCode) {
                outputAirport = airport;
            }
        }
        return outputAirport;
    }
}
