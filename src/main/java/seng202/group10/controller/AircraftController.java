package seng202.group10.controller;

import seng202.group10.model.Aircraft;

import java.util.ArrayList;

public class AircraftController {

    /**
     * Creates and returns an object of Class Aircraft from the parameters
     * @param name aircraft name
     * @param iata iata code
     * @param icao icao code
     * @param fuelRate fuel rate of aircraft
     * @return Aircraft
     */
    public Aircraft addAircraft(String name, String iata, String icao, double fuelRate) {
        return new Aircraft(name, iata, icao, fuelRate);
    }

    /**
     * returns ArrayList of all created Aircraft's
     */
    public void getAircrafts() { // will return type ArrayList<Aircraft>

    }

    /**
     * deletes Aircraft from ArrayList
     * @param craft Aircraft
     */
    public void deleteAircraft(Aircraft craft) {

    }
}
