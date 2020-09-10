package seng202.group10.model;

import java.util.ArrayList;

public class AirlineModel {

    private AirlineRW airlineRW;

    private ArrayList<Airline> airlines = new ArrayList<>();

    public AirlineModel() {
        airlineRW = new AirlineRW();
        ArrayList<Airline> databaseAirlines = airlineRW.readDatabaseAirlines();
        System.out.println("start");
        for (Airline airline: databaseAirlines) {
            System.out.println("loop");
            airlines.add(airline);
        }
    }

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
            airlineRW.writeDatabaseAirline(airline);
            airlines.add(airline);
        }
    }

    public void deleteAirline(Airline airline) { airlines.remove(airline); }
}
