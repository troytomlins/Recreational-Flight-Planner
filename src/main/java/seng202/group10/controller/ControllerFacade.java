package seng202.group10.controller;

import seng202.group10.model.Flight;
import seng202.group10.model.ModelFacade;
import seng202.group10.view.AirportTabController;
import seng202.group10.view.FlightTabController;

public class ControllerFacade {
    private DataController dataController;
    private RawDataController rawDataController;
    private AircraftController aircraftController;
    private FlightPlanner flightPlanner;
    private MapController mapController;
    private AirlineController airlineController;
    private ModelFacade model;
    private AirportController airportController;
    private AirportTabController airportTabController;
    private RouteController routeController;
    private FlightController flightController;

    /**
     * Sets the Model Facade to input
     * @param modelFacade ModelFacade
     */
    public void setModel(ModelFacade modelFacade) {
        model = modelFacade;
    }

    public ControllerFacade() {
        this.airlineController = new AirlineController();
        this.airportController = new AirportController();
        this.dataController = new DataController();
        this.rawDataController = new RawDataController();
        this.aircraftController = new AircraftController();
        this.flightPlanner = new FlightPlanner();
        this.mapController = new MapController();
        this.model = new ModelFacade();
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
