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
                        dataLine.get(1),
                        dataLine.get(3),
                        Integer.parseInt(dataLine.get(7))
                );
                routeList.add(route);
            }
        }
        return routeList;
    }

    public void writeRoute(ArrayList<Route> routes) {
        ArrayList<ArrayList<String>> routeStrings = new ArrayList<ArrayList<String>>();

        for (Route route: routes) {
            routeStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            route.getAirlineCode(),
                            route.getSourceAirportCode(),
                            route.getDestinationAirportCode(),
                            Integer.toString(route.getStops())
                    )));
        }
        writeAll(routeStrings);
    }
}
