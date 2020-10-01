package seng202.group10.controller.filters;

import seng202.group10.model.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to filter the route data.
 * @author Niko Tainui
 * @author Johnny Howe
 * @author Mitchell Freeman
 */
public class RouteFilters extends GenericFilters{

    /**
     * Constructor for RouteFilters.
     * Sets table to "routes".
     */
    public RouteFilters() {
        super("routes");
    }

    /**
     * Apply filters and returns list of Routes fitting applied conditions.
     * @return ArrayList of Route
     */
    public ArrayList<Route> applyFilters() {

        ArrayList<Route> resultRoutes = new ArrayList<>();

        ResultSet results = filterSender.applyFilter();
        try {
            while (results.next()) {
                resultRoutes.add(new Route(
                        results.getString("airlineCode"),
                        results.getString("sourceAirportCode"),
                        results.getString("destinationAirportCode"),
                        results.getInt("stops")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultRoutes;
    }
}