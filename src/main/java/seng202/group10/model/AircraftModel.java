package seng202.group10.model;

import java.util.ArrayList;

public class AircraftModel {
    private ArrayList<Aircraft> aircraft;

    /**
     * adds new aircraft to ArrayList aircraft
     * @param craft
     */
    public void addAircraft(Aircraft craft) {
        aircraft.add(craft);
    }

    /**
     * removes aircraft from ArrayList aircraft
     * @param craft
     */
    public void deleteAircraft(Aircraft craft) {
        aircraft.remove(craft);
    }
}
