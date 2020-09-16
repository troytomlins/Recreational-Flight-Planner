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
    /**
     * Get a new Arraylist of all the routes with contains string in airline code
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by airline containing contains
     */
    public ArrayList<Route> filterByAirline(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, contains, "", "", -1);
    }

    /**
     * Get a new Arraylist of all the routes with contains string source
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by source containing contains
     */
    public ArrayList<Route> filterBySource(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, "", contains, "", -1);
    }

    /**
     * Get a new Arraylist of all the routes with contains string in destination
     * @param routeData data to filter through
     * @param contains substring to filter for
     * @return the route data filtered by destination containing contains
     */
    public ArrayList<Route> filterByDestination(ArrayList<Route> routeData, String contains) {
        return filterByAll(routeData, "", "", contains, -1);
    }

    /**
     * Does this route pass the test for the maximum number of stops?
     * if maxNumStops is -1, then return value is true
     * @param route
     * @param maxNumStops
     * @return whether the route has stops less or equal to maxNumStops
     */
    private boolean checkNumStops(Route route, int maxNumStops) {
        return (maxNumStops == -1 || route.getStops() <= maxNumStops);
    }

    //TODO write error checking for filters making sure data is loaded.
    /**
     * Get a new Arraylist of all the routes
     * Super filter method - no point repeating the same code in multiple methods
     * @param routeData data to filter through
     * @param airlineContains substring to filter for in the route airlines
     * @param sourceContains substring to filter for in the route sources
     * @param destinationContains substring to filter for in the route destinations
     * @param maxNumStops maximum stops in route to filter. for no max, set to -1
     * @return the airport data filtered by airlineContains, sourceContains and destinationContains
     */
    public ArrayList<Route> filterByAll(ArrayList<Route> routeData, String airlineContains, String sourceContains, String destinationContains, int maxNumStops) {
        ArrayList<Route> filteredRoutes = new ArrayList<>();
        // Does at least one filter exist?
        if (!(airlineContains.equals("") && sourceContains.equals("") && destinationContains.equals("") && maxNumStops > 1)) {
            for (Route route : routeData) {
                // Does the airport pass the filters?
                if (checkNumStops(route, maxNumStops) && route.getAirlineCode().contains(airlineContains) && route.getSourceAirportCode().contains(sourceContains) && route.getDestinationAirportCode().contains(destinationContains)) {
                    filteredRoutes.add(route);
                }
            }
        } else {
            filteredRoutes = routeData;
        }
        return filteredRoutes;
    }
}