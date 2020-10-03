package seng202.group10.controller;

import seng202.group10.view.AirportTabController;

/**
 * Controller Facade Class for holding all controllers.
 */
public class ControllerFacade {
    private AircraftController aircraftController;
    private FlightPlanner flightPlanner;
    private AirlineController airlineController;
    private AirportController airportController;
    private AirportTabController airportTabController;
    private RouteController routeController;
    private FlightController flightController;

    /**
     * Constructor for ControllerFacade Class.
     * Creates new controllers.
     */
    public ControllerFacade() {
        this.airlineController = new AirlineController();
        this.airportController = new AirportController();
        this.aircraftController = new AircraftController();
        this.flightPlanner = new FlightPlanner();
        this.routeController = new RouteController();
        this.flightController = new FlightController();
    }

    public AirlineController getAirlineController() {
        return airlineController;
    }

    public AirportController getAirportController() {
        return this.airportController;
    }

    public RouteController getRouteController () { return this.routeController; }

    public AircraftController getAircraftController () { return this.aircraftController; }

    public FlightController getFlightController() { return this.flightController; }
}
