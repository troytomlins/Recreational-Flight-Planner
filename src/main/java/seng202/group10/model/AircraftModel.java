package seng202.group10.model;

import java.util.ArrayList;

/**
 * @author Tom Rizzi
 */

public class AircraftModel {
    private ArrayList<Aircraft> aircraft;
    private AircraftRW aircraftRW = new AircraftRW();

    public AircraftModel() {
        aircraft = new ArrayList<Aircraft>();
        ArrayList<Aircraft> databaseAircraft = aircraftRW.readDatabaseAircrafts();
        for (Aircraft aircraftSingle: databaseAircraft) {
            aircraft.add(aircraftSingle);
        }
    }

    /**
     * adds new aircraft to ArrayList aircraft
     * @param craft Class Aircraft
     */
    public void addAircraft(Aircraft craft) {
        if (!aircraft.contains(craft)) {
            ArrayList<Aircraft> toAdd = new ArrayList<Aircraft>();
            toAdd.add(craft);
            aircraftRW.writeDatabaseAircrafts(toAdd);
        }
        aircraft = aircraftRW.readDatabaseAircrafts();
    }

    /**
     * removes aircraft from ArrayList aircraft
     * @param craft Class Aircraft
     */
    public void deleteAircraft(Aircraft craft) {
        aircraft.remove(craft);
    }

    public ArrayList<Aircraft> getAircraftList() {
        return aircraft;
    }
}
