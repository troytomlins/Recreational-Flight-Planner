package seng202.group10.model;

import java.util.ArrayList;

/**
 * Holds a list of Airline's in an ArrayList.
 * @author Mitchell Freeman
 */

public class AirlineModel {

    private AirlineRW airlineRW;

    private ArrayList<Airline> airlines = new ArrayList<>();
    private ArrayList<Airline> unsavedAirlines = new ArrayList<>();

    /**
     * Constructor to set the read and writer and make a new ArrayList of Aircraft's from the database.
     */
    public AirlineModel() {
        airlineRW = new AirlineRW();
        ArrayList<Airline> databaseAirlines = airlineRW.readDatabaseAirlines();
        for (Airline airline: databaseAirlines) {
            airlines.add(airline);
        }
    }

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    /**
     * Adds an Airline object to an ArrayList of Airline objects.
     * If the Airline object is already in the ArrayList, the Airline object is not added.
     * @param airline an Airline object.
     */
    public void addAirline(Airline airline) {
        if (!airlines.contains(airline)) {
            unsavedAirlines.add(airline);
        }
    }

    /**
     * Saves all unsaved airlines to the database and updates the airlines ArrayList.
     */
    public void save() {
        airlineRW.writeDatabaseAirlines(unsavedAirlines);
        unsavedAirlines = new ArrayList<>();
        airlines = airlineRW.readDatabaseAirlines();
    }

//    public void deleteAirline(Airline airline) { airlines.remove(airline); }
}
