package seng202.group10.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author Mitchell
 */
public class RouteRW extends RWStream {
    public RouteRW(String inFile) {
        super(inFile, "route.csv");
    }

    public RouteRW() {
        super("route.csv");
    }

    public ArrayList<Route> readRoutes() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Route> routeList = new ArrayList<Route>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateRoute(dataLine)*/) {
                Scanner scanner = new Scanner(dataLine.get(8));
                scanner.useDelimiter(" ");

                ArrayList<String> equipment = new ArrayList<String>();

                while (scanner.hasNext()) {
                    equipment.add(scanner.next());
                }

                Route route = new Route(
                        dataLine.get(0),
                        dataLine.get(2),
                        dataLine.get(4),
                        Integer.parseInt(dataLine.get(7)),
                        equipment
                );
                routeList.add(route);
            }
        }
        return routeList;
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
