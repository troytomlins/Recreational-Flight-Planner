package seng202.group10.model;

import java.util.ArrayList;

/**
 * Holds a list of Airport's in an ArrayList.
 */
public class AirportModel {

    private ArrayList<Airport> airports;
    private ArrayList<Airport> unsavedAirports;

    private AirportRW airportRW;

    /**
     * Constructor to set the read and writer and make a new ArrayList of Airline's from the database.
     */
    public AirportModel() {
        this.airports = new ArrayList<>();
        this.unsavedAirports = new ArrayList<>();

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
        unsavedAirports.add(airport);
    }

    /**
     * Returns the index in arraylist of the airport with the same values as provided.
     * @param airport Airport to look for in list
     * @return Index in arraylist or -1 if non existant
     */
    public int getIndexOfAirport(Airport airport) {
        for (int i = 0; i < airports.size(); i++) {
            Airport cur = airports.get(i);
            if (cur.sameValues(airport)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Deletes an airport from model
     * @param airport Airport to delete
     */
    public void deleteAirport(Airport airport) {
        airportRW.deleteDatabaseAirports(airport);
        if (getIndexOfAirport(airport) > -1) {
            airports.remove(getIndexOfAirport(airport));
        }
        save();
    }

    /**
     * Saves all unsaved airports to the database and updates the airports ArrayList.
     */
    public void save() {
        airportRW.writeDatabaseAirports(unsavedAirports);
        unsavedAirports = new ArrayList<>();
        airports = airportRW.readDatabaseAirports();
    }

    /**
     * Saves all current airports in file to specified filepath
     * @param filepath Filepath to save airports to
     */
    public void saveToFile(String filepath) {
        AirportRW stream = new AirportRW();
        stream.setOutFileName(filepath);
        stream.writeAirports(airports);
    }

}
