package seng202.group10.model;

import java.util.ArrayList;

public class AirportModel {

    private ArrayList<Airport> airports;
    private ArrayList<Airport> unsavedAirports;

    private AirportRW airportRW;

    public AirportModel() {
        this.airports = new ArrayList<Airport>();
        this.unsavedAirports = new ArrayList<Airport>();

        airportRW = new AirportRW();

        ArrayList<Airport> databaseAirports = airportRW.readDatabaseAirports();
        for (Airport airport: databaseAirports) {
            airports.add(airport);
        }
    }

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
            unsavedAirports.add(airport);
        }
    }

    public void save() {
        airportRW.writeDatabaseAirports(unsavedAirports);
        unsavedAirports = new ArrayList<Airport>();
        airports = airportRW.readDatabaseAirports();
    }

}
