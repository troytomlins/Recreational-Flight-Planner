package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.Route;
import seng202.group10.model.RouteRW;

import java.util.ArrayList;

/**
 * Class to filter the route data.
 * @author Niko Tainui
 * @author Johnny Howe
 */

public class RouteFilters {
    // airlineCode, sourceAirportCode, destAirportCode

    /**
     * Get a new Arraylist of all the routes with contains string in airline code
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by airline containing contains
     */
    public ArrayList<Route> filterByAirline(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, contains, "", "");
    }

    /**
     * Get a new Arraylist of all the routes with contains string source
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by source containing contains
     */
    public ArrayList<Route> filterBySource(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, "", contains, "");
    }

    /**
     * Get a new Arraylist of all the routes with contains string in destination
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by destination containing contains
     */
    public ArrayList<Route> filterByDestination(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, "", "", contains);
    }

    //TODO write error checking for filters making sure data is loaded.
    /**
     * Get a new Arraylist of all the airlines
     * Super filter method - no point repeating the same code in multiple methods
     * @param routeData data to filter through
     * @param airlineContains substring to filter for in the route airlines
     * @param sourceContains substring to filter for in the route sources
     * @param destinationContains substring to filter for in the route destinations
     * @return the airport data filtered by airlineContains, sourceContains and destinationContains
     */
    public ArrayList<Route> filterByAll(ArrayList<Route> routeData, String airlineContains, String sourceContains, String destinationContains) {
        ArrayList<Route> filteredRoutes = new ArrayList<>();
        // Does at least one filter exist?
        if (!(airlineContains.equals("") && sourceContains.equals("") && destinationContains.equals(""))) {
            for (Route route : routeData) {
                // Does the airport pass the filters?
                if (route.getAirlineCode().contains(airlineContains) && route.getSourceAirportCode().contains(sourceContains) && route.getDestinationAirportCode().contains(destinationContains)) {
                    filteredRoutes.add(route);
                }
            }
        }
        return filteredRoutes;
    }
}