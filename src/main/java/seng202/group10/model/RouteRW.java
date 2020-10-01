package seng202.group10.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Read/Write Class for Route
 * @author Mitchell Freeman, Tom Rizzi
 */
public class RouteRW extends RWStream {
    public RouteRW(String inFile) {
        super(inFile, "route.csv");
    }

    public RouteRW() {
        super("route.csv");
    }

    /**
     * Reads routes from routes data file
     * @return List of routes read from the file
     * @throws FileFormatException Wrong File Format
     * @throws IncompatibleFileException Incompatible File
     */
    public ArrayList<Route> readRoutes() throws FileFormatException, IncompatibleFileException {
        return readRoutes(new ArrayList<>());
    }

    /**
     * Read routes from routes data file
     * @param ignoreLines List of line indices to ignore (1 origin)
     * @return List of routes read from the file
     * @throws IncompatibleFileException Incompatible File
     * @throws FileFormatException Wrong File Format
     */
    public ArrayList<Route> readRoutes(ArrayList<Integer> ignoreLines) throws IncompatibleFileException, FileFormatException {
        // Initialise file reader and airports list
        ArrayList<Route> routes = new ArrayList<>();
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(super.getInFilename()));
        } catch (Exception e) {
            throw new IncompatibleFileException();
        }

        try {
            // Parse each line
            CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
            Integer lineNum = 1;
            ArrayList<Integer> errorLines = new ArrayList<>();

            try {
                for (CSVRecord csvRecord : parser) {

                    // Check if the line is to be ignored
                    if (!ignoreLines.contains(lineNum)) {
                        try {
                            // Get corresponding values from the csv record
                            Route route = new Route(
                                    csvRecord.get(0),
                                    csvRecord.get(2),
                                    csvRecord.get(4),
                                    Integer.parseInt(csvRecord.get(7))
                            );
                            routes.add(route);

                        } catch (Exception e) {

                            // There was an error parsing the line, add to errorLines
                            errorLines.add(lineNum);
                        }
                    }
                    // Increment line counter
                    lineNum++;
                }
            } catch (IllegalStateException err) {
                throw new IncompatibleFileException();
            }

            // Deal with any errors
            if (errorLines.size() == (lineNum-1)) {
                // Every single line caused an error
                throw new IncompatibleFileException();
            } else if (errorLines.size() > 0) {
                // Some of the lines had errors
                throw new FileFormatException(errorLines, super.getInFilename());
            }

            return routes;
        } catch (IOException e) {
            throw new IncompatibleFileException();
        } finally {
            // Close reader
            try {
                csvReader.close();
            } catch (IOException ignored) {}
        }
    }


    public ArrayList<Route> readRoutes1() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Route> routeList = new ArrayList<>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateRoute(dataLine)*/) {
                Scanner scanner = new Scanner(dataLine.get(8));
                scanner.useDelimiter(" ");

                ArrayList<String> equipment = new ArrayList<>();

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

    /**
     * Reads routes from database and returns Routes.
     * @return ArrayList of Route
     */
    public ArrayList<Route> readDatabaseRoutes() {
        ResultSet results = databaseConnection.executeQuery("SELECT * FROM routes");

        ArrayList<Route> output = new ArrayList<>();

        try {
            while (results.next()) {
                output.add(new Route(
                        results.getString("airlineCode"),
                        results.getString("sourceAirportCode"),
                        results.getString("destinationAirportCode"),
                        results.getInt("stops")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * Writes Route's to database.
     * @param routes ArrayList of Route
     */
    public void writeDatabaseRoutes(ArrayList<Route> routes) {
        databaseConnection.setAutoCommit(false);
        for (Route route: routes) {
            try {
                PreparedStatement pStatement = databaseConnection.getFormattedPreparedStatement(
                        "INSERT INTO routes (airlineCode, sourceAirportCode, destinationAirportCode, stops)",
                        4
                );
                pStatement.setString(1, route.getAirlineCode());
                pStatement.setString(2, route.getSourceAirportCode());
                pStatement.setString(3, route.getDestinationAirportCode());
                pStatement.setInt(4, route.getStops());

                pStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        databaseConnection.commit();
        databaseConnection.setAutoCommit(true);
    }

    /**
     * Uses the super class RWStream to write all a route to a file.
     * The Route object attributes have to be converted to strings.
     * @param routes An ArrayList of Route objects.
     */
    public void writeRoute(ArrayList<Route> routes) {
        ArrayList<ArrayList<String>> routeStrings = new ArrayList<>();

        for (Route route: routes) {
            routeStrings.add(
                    new ArrayList<>(Arrays.asList(
                            route.getAirlineCode(),
                            null, // Field not preserved in database.
                            route.getSourceAirportCode(),
                            null, // Field not preserved in database.
                            route.getDestinationAirportCode(),
                            null, // Field not preserved in database.
                            null, // Field not preserved in database.
                            Integer.toString(route.getStops()),
                            null  // Field not preserved in database.
                    )));
        }
        writeAll(routeStrings);
    }

}
