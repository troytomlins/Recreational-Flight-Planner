package seng202.group10.controller;

import seng202.group10.model.Aircraft;
import seng202.group10.model.AircraftModel;

import java.util.ArrayList;

/**
 * Controller Class for Aircraft.
 */
public class AircraftController {

    private AircraftModel model;

    /**
     * Constructor for AircraftController.
     * Sets model to a new instance of AircraftModel
     */
    public AircraftController() {
        this.model = new AircraftModel();
    }

    /**
     * Creates and returns an object of Class Aircraft from the parameters.
     * @param name aircraft name
     * @param iata iata code
     * @param icao icao code
     * @param fuelRate fuel rate of aircraft
     */
    public void addAircraft(String name, String iata, String icao, double fuelRate) {
        model.addAircraft(new Aircraft(name, iata, icao, fuelRate));
    }

    /**
     * Returns all Aircraft in model.
     * @return ArrayList of all created Aircraft.
     */
    public ArrayList<Aircraft> getAircraft() { // will return type ArrayList<Aircraft>
        return model.getAircraftList();
    }

//    /**
//     * Deletes Aircraft from ArrayList.
//     * @param craft Aircraft to delete
//     */
//    public void deleteAircraft(Aircraft craft) {
//        model.deleteAircraft(craft);
//    }

}
