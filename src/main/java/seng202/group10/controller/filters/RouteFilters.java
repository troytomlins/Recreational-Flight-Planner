package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.Route;
import seng202.group10.model.RouteRW;

import java.util.ArrayList;

/**
 * @author Niko Tainui
 */

public class RouteFilters {
    /**
     * Get a new Arraylist of all the routes which contains a specific string in different fields.
     *
     * @param contains (String): substring to filter for
     * @return ArrayList<Route>
     */

    public ArrayList<Route> filterByAirlineCode(ArrayList<Route> routeData, String contains) {
        ArrayList<Route> filteredRoutes = new ArrayList<>();
        if (contains == "") {
            filteredRoutes = routeData;
        } else {

            for (Route route : routeData) {
                if (route.getAirlineCode().contains(contains)) {
                    filteredRoutes.add(route);
                }
            }
        }
        return filteredRoutes;
    }

    public ArrayList<Route> filterBySourceAirportCode(ArrayList<Route> routeData, String contains) {
        ArrayList<Route> filteredRoutes = new ArrayList<>();
        if (contains == "") {
            filteredRoutes = routeData;
        } else {
            for (Route route : routeData) {
                if (route.getSourceAirportCode().contains(contains)) {
                    filteredRoutes.add(route);
                }
            }
        }
        return filteredRoutes;
    }

    public ArrayList<Route> filterByDestinationAirportCode(ArrayList<Route> routeData, String contains) {
        ArrayList<Route> filteredRoutes = new ArrayList<>();
        if (contains == "") {
            filteredRoutes = routeData;
        } else {
            for (Route route : routeData) {
                if (route.getDestinationAirportCode().contains(contains)) {
                    filteredRoutes.add(route);
                }
            }
        }
        return filteredRoutes;
    }

    public ArrayList<Route> filterByAll(ArrayList<Route> routeData, String airlineCode, String srcAirportCode, String dstAirportCode, Integer numStops) {
        ArrayList<Route> routesByAirline = new ArrayList<>();
        ArrayList<Route> routesByAirlineSrc = new ArrayList<>();
        ArrayList<Route> routesByAirlineSrcDest = new ArrayList<>();
        routesByAirline = filterByAirlineCode(routeData, airlineCode);
        routesByAirlineSrc = filterBySourceAirportCode(routesByAirline, srcAirportCode);
        routesByAirlineSrcDest = filterByDestinationAirportCode(routesByAirlineSrc, dstAirportCode);
        return routesByAirlineSrcDest;
    }




}