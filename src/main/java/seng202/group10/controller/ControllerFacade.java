package seng202.group10.controller;

import seng202.group10.model.ModelFacade;

public class ControllerFacade {
    private DataController dataController;
    private RawDataController rawDataController;
    private AircraftController aircraftController;
    private FlightPlanner flightPlanner;
    private MapController mapController;
    private AirlineController airlineController;
    private ModelFacade model;

    /**
     * Sets the Model Facade to input
     * @param modelFacade ModelFacade
     */
    public void setModel(ModelFacade modelFacade) {
        model = modelFacade;
    }

    public ControllerFacade() {
        this.airlineController = new AirlineController();
    }

    public AirlineController getAirlineController() {
        return airlineController;
    }

}
