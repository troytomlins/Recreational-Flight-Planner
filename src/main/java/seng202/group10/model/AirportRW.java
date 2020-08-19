package seng202.group10.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Mitchell
 */
public class AirportRW extends RWStream {


    public AirportRW(String inFile) {
        super(inFile, "airport.csv");
    }

    public AirportRW() {
        super("airport.csv");
    }

    public ArrayList<Airport> readAirports() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Airport> airportList = new ArrayList<Airport>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateAirport(dataLine)*/) {
                Airport airport = new Airport(
                                              dataLine.get(1),
                                              dataLine.get(2),
                                              dataLine.get(3),
                                              dataLine.get(4),
                                              dataLine.get(5),
                                              Float.parseFloat(dataLine.get(6)),
                                              Float.parseFloat(dataLine.get(7)),
                                              Float.parseFloat(dataLine.get(8)),
                                              Float.parseFloat(dataLine.get(9)),
                                              dataLine.get(10).charAt(0),
                                              dataLine.get(11)
                );
                airportList.add(airport);
            }
        }
        return airportList;
    }

    public void writeAirports(ArrayList<Airport> airports) {
        ArrayList<ArrayList<String>> airportStrings = new ArrayList<ArrayList<String>>();

        for (Airport airport: airports) {
            airportStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            airport.getName(),
                            airport.getCity(),
                            airport.getCountry(),
                            airport.getIata(),
                            airport.getIcao(),
                            Float.toString(airport.getLatitude()),
                            Float.toString(airport.getLongitude()),
                            Float.toString(airport.getAltitude()),
                            Float.toString(airport.getTimezone()),
                            Character.toString(airport.getDstType()),
                            airport.getTzDatabase()
            )));
        }
        writeAll(airportStrings);
    }
}
