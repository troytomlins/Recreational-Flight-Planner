package seng202.group10.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Niko
 */

public class AircraftRW extends RWStream {

    public AircraftRW(String inFile) {
        super(inFile, "aircraft.csv");
    }

    public AircraftRW() {
        super("aircraft.csv");
    }

    public ArrayList<Aircraft> readAircraft() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateAircraft(dataLine)*/) {
                Aircraft aircraft = new Aircraft(
                        dataLine.get(0),
                        dataLine.get(1),
                        dataLine.get(2),
                        Double.parseDouble(dataLine.get(3))
                );
                aircraftList.add(aircraft);
            }
        }
        return aircraftList;
    }

    public void writeAircrafts(ArrayList<Aircraft> aircrafts) {
        ArrayList<ArrayList<String>> aircraftStrings = new ArrayList<ArrayList<String>>();

        for (Aircraft aircraft: aircrafts) {
            aircraftStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            aircraft.getIata(),
                            aircraft.getName(),
                            aircraft.getIcao(),
                            Double.toString(aircraft.getRange())
                    )));
        }
        writeAll(aircraftStrings);
    }
}
