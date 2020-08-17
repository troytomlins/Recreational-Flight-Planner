package seng202.group10.model;

import java.util.ArrayList;
import java.util.Arrays;

public class AirlineRW extends RWStream {
    public AirlineRW(String inFile) {
        super(inFile, "airline.csv");
    }

    public AirlineRW() {
        super("airline.csv");
    }

    public ArrayList<Airline> readAirlines() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Airline> airlineList = new ArrayList<Airline>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateAirline(dataLine)*/) {
                Airline airline = new Airline(
                        dataLine.get(1),
                        dataLine.get(2),
                        dataLine.get(3),
                        dataLine.get(4),
                        dataLine.get(5),
                        dataLine.get(6)

                );
                airlineList.add(airline);
            }
        }
        return airlineList;
    }

    public void writeAirlines(ArrayList<Airline> airlines) {
        ArrayList<ArrayList<String>> airlineStrings = new ArrayList<ArrayList<String>>();

        for (Airline airline: airlines) {
            airlineStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            airline.getName(),
                            airline.getAlias(),
                            airline.getIata(),
                            airline.getIcao(),
                            airline.getCallsign(),
                            airline.getCountry()
                    )));
        }
        writeAll(airlineStrings);
    }
}