package seng202.group10.model;

import java.util.ArrayList;

public class RouteModel {

    private ArrayList<Route> routes;

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * addRoute adds a Route object to an ArrayList of Route objects.
     * If the Route object is already in the ArrayList, the Route object is not added.
     * @param route a Route object.
     */
    public void addRoute(Route route) {
        if (!routes.contains(route)) {
            routes.add(route);
        }
    }
}
