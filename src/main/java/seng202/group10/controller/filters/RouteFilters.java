package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.Route;
import seng202.group10.model.RouteRW;

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
    public RouteFilters() {
        super("routes");
    }

    public ArrayList<Route> applyFilters() {
        ArrayList<Route> resultRoutes = new ArrayList<Route>();

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