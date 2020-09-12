package seng202.group10.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public ArrayList<Route> readRoutes() throws IOException, IncompatibleFileException {
        ArrayList<Route> routes = new ArrayList<>();

        // Initialise file reader and string row variable
        BufferedReader csvReader = new BufferedReader(new FileReader(super.getInFilename()));

        // Parse each line
        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {
            try {
                // Get corresponding values from the csv record
                Route route = new Route(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        csvRecord.get(3),
                        Integer.parseInt(csvRecord.get(7))
                );
                routes.add(route);
            } catch (Exception e) {
                throw new IncompatibleFileException();
            }
        }

        // Close reader
        csvReader.close();
        return routes;
    }

    public ArrayList<Route> readRoutes1() {
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
