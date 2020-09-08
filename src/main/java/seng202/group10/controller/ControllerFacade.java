package seng202.group10.controller;

import seng202.group10.model.Aircraft;
import seng202.group10.model.ModelFacade;

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
    }

    public AirlineController getAirlineController() {
        return airlineController;
    }

    public AirportController getAirportController() {
        return this.airportController;
    }


}
