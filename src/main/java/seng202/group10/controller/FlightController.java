package seng202.group10.controller;

import seng202.group10.model.Flight;
import seng202.group10.model.FlightModel;
import seng202.group10.model.FlightRW;
import seng202.group10.model.IncompatibleFileException;

import java.io.IOException;
import java.util.ArrayList;

public class FlightController {

    private FlightModel model;

    /**
     * Constructor
     */
    public FlightController() {
        this.model = new FlightModel();
    }

    /**
     * Import flight
     * @param filepath Filepath for flight to import
     */
    public void importFlight(String filepath) throws IOException, IncompatibleFileException {
        FlightRW stream = new FlightRW(filepath);
        Flight flight = stream.readFlight();
        model.addFlight(flight);
    }

    public void addFlight(Flight flight) {
        model.addFlight(flight);
    }

    public ArrayList<Flight> getFlights() {
        return model.getFlights();
    }

}
