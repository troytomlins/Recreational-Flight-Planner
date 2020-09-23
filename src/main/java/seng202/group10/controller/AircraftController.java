package seng202.group10.controller;

import seng202.group10.model.Aircraft;
import seng202.group10.model.AircraftModel;
import seng202.group10.model.AirlineModel;

import java.util.ArrayList;


public class AircraftController {

    /**
     * Model
     */
    private AircraftModel model;

    public AircraftController() {
        this.model = new AircraftModel();
    }


    /**
     * Creates and returns an object of Class Aircraft from the parameters
     * @param name aircraft name
     * @param iata iata code
     * @param icao icao code
     * @param fuelRate fuel rate of aircraft
     */
    public void addAircraft(String name, String iata, String icao, double fuelRate) {
        model.addAircraft(new Aircraft(name, iata, icao, fuelRate));
    }

    /**
     * @return ArrayList of all created Aircrafts
     */
    public ArrayList<Aircraft> getAircraft() { // will return type ArrayList<Aircraft>
        return model.getAircraftList();
    }

    /**
     * deletes Aircraft from ArrayList
     * @param craft Aircraft to delete
     */
    public void deleteAircraft(Aircraft craft) {
        model.deleteAircraft(craft);
    }

}
