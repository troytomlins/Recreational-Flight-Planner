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
        if (getIndexOf(craft) == -1) {
            ArrayList<Aircraft> toAdd = new ArrayList<Aircraft>();
            toAdd.add(craft);
            aircraftRW.writeDatabaseAircrafts(toAdd);
        }
        aircraft = aircraftRW.readDatabaseAircrafts();
    }

    /**
     * Gets the index of an aircraft with the same values in the list.
     * @param craft Aircraft to look for in the list
     * @return Index of the aircraft or -1 if not found.
     */
    public int getIndexOf(Aircraft craft) {

        for (int i = 0; i < aircraft.size(); i++) {
            Aircraft oldCraft = aircraft.get(i);
            if (oldCraft.sameValues(craft)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * removes aircraft from ArrayList aircraft
     * @param craft Class Aircraft
     */
    public void deleteAircraft(Aircraft craft) {
        for (Aircraft oldCraft : aircraft) {
            if (oldCraft.sameValues(craft)) {
                aircraft.remove(oldCraft);
                aircraftRW.deleteDatabaseAircraft(craft);
                return;
            }
        }

    }

    public ArrayList<Aircraft> getAircraftList() {
        return aircraft;
    }
}
