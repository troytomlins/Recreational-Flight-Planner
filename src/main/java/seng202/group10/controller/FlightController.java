package seng202.group10.controller;

import seng202.group10.model.*;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Retrieves flight data from the model
 * @author Johnny Howe
 */
public class FlightController {

    private FlightModel model;

    public FlightController() {
        this.model = new FlightModel();
    }

    /**
     * Import flight
     * @param filepath Filepath for flight to import
     * @throws IncompatibleFileException when a non csv file is given
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    public void importFlight(String filepath) throws IOException, IncompatibleFileException {
        FlightRW stream = new FlightRW(filepath);
        Flight flight = stream.readFlight();
        model.addFlight(flight);
    }

    public ArrayList<Flight> getFlights() {
        return model.getFlights();
    }

}
