package seng202.group10.model;

import java.util.ArrayList;

public class RouteModel {

    private ArrayList<Route> routes = new ArrayList<>();
    private ArrayList<Route> unsavedRoutes = new ArrayList<>();

    private RouteRW routeRW = new RouteRW();

    public RouteModel() {
        ArrayList<Route> databaseRoutes = routeRW.readDatabaseRoutes();
        for (Route route: databaseRoutes) {
            routes.add(route);
        }
    }

    /**
     * getRoutes returns an ArrayList of Route objects.
     * @return routes a ArrayList<Routes> object.
     */
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
            unsavedRoutes.add(route);
        }
    }

    public void save() {
        routeRW.writeDatabaseRoutes(unsavedRoutes);
        unsavedRoutes = new ArrayList<>();
        routes = routeRW.readDatabaseRoutes();
    }

    public void deleteRoute(Route route) { routes.remove(route); }
}
