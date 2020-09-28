package seng202.group10.controller;

import seng202.group10.model.Flight;
import seng202.group10.model.FlightModel;
import seng202.group10.model.FlightRW;
import seng202.group10.model.IncompatibleFileException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller Class for Flight.
 */
public class FlightController {

    private FlightModel model;

    /**
     * Constructor for FlightController.
     * Set model as a new instance of FlightModel.
     */
    public FlightController() {
        this.model = new FlightModel();
    }

    /**
     * Import flight from file.
     * @param filepath Filepath for flight to import
     */
    public void importFlight(String filepath) throws IOException, IncompatibleFileException {
        FlightRW stream = new FlightRW(filepath);
        Flight flight = stream.readFlight();
        model.addFlight(flight);
    }

    /**
     * Returns a list of Flights in model.
     * @return ArrayList of Class Flight
     */
    public ArrayList<Flight> getFlights() {
        return model.getFlights();
    }

}
