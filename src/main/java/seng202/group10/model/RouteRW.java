package seng202.group10.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ArrayList<Route> readDatabaseRoutes() {
        ResultSet results = databaseConnection.executeQuery("SELECT * FROM routes");

        ArrayList<Route> output = new ArrayList<Route>();

        try {
            while (results.next()) {
                output.add(new Route(
                        results.getString("airline"),
                        results.getString("sourceAirport"),
                        results.getString("destinationAirport"),
                        results.getInt("stops")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

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
                ResultSet autoIncrement = pStatement.getGeneratedKeys();
                autoIncrement.next();
                int id = autoIncrement.getInt(1);

                String updateStatement = "UPDATE routes" +
                        "SET airline = (" +
                        "   SELECT id" +
                        "   FROM airlines" +
                        "   WHERE iata = ? OR icao = ?)," +
                        "sourceAirport = (" +
                        "   SELECT id" +
                        "   FROM airports" +
                        "   WHERE iata = ? OR icao = ?)," +
                        "destinationAirport = (" +
                        "   SELECT id" +
                        "   FROM airports" +
                        "   WHERE iata = ? OR icao = ?)" +
                        "WHERE id = ?";

                PreparedStatement updatePStatement = databaseConnection.getPreparedStatement(updateStatement);

                updatePStatement.setString(1, route.getAirlineCode());
                updatePStatement.setString(2, route.getAirlineCode());

                updatePStatement.setString(3, route.getSourceAirportCode());
                updatePStatement.setString(4, route.getSourceAirportCode());

                updatePStatement.setString(5, route.getDestinationAirportCode());
                updatePStatement.setString(6, route.getDestinationAirportCode());

                updatePStatement.setInt(7, id);


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        databaseConnection.commit();
        databaseConnection.setAutoCommit(true);
    }
}
