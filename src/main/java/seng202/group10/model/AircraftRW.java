package seng202.group10.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Niko Tainui
 */

public class AircraftRW extends RWStream {

    /**
     * creates Aircraft read writer with a defined infile and a default outfile
     * @param inFile
     */
    public AircraftRW(String inFile) {
        super(inFile, "aircraft.csv");
    }

    /**
     * creates Aircraft read writer with a default infile
     */
    public AircraftRW() {
        super("aircraft.csv");
    }

    /**
     * reads the file output of a aircraft datasheet
     * @return list of Aircraft objects
     */
    public ArrayList<Aircraft> readAircraft() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateAircraft(dataLine)*/) {
                Aircraft aircraft = new Aircraft(
                        //Iata
                        dataLine.get(0),
                        //Name
                        dataLine.get(1),
                        //Icao
                        dataLine.get(2),
                        //Range
                        Double.parseDouble(dataLine.get(3))
                );
                aircraftList.add(aircraft);
            }
        }
        return aircraftList;
    }

    /**
     * takes aircraft objects as an arraylist and writes it to the outfile
     * @param aircrafts
     */

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
