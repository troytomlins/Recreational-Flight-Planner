package seng202.group10.controller;

import seng202.group10.model.*;

import java.util.ArrayList;

/**
 * Controller Class for Airport.
 */
public class AirportController {

    private AirportModel model;

    /**
     * Constructor for AirportController.
     * Sets model to an inputted instance of AirportModel.
     */
    public AirportController(AirportModel model) {
        this.model = model;
    }

    /**
     * Constructor for AirportController Class.
     * Creates a new instance of AirportModel.
     */
    public AirportController() {
        this.model = new AirportModel();
    }

    /**
     * Get a list of airports from model.
     * @return Arraylist of airports
     */
    public ArrayList<Airport> getAirports() {
        return model.getAirports();
    }

    /**
     * Takes a filepath and imports all airports from the file into model except for lines in ignoreLines.
     * @param filePath Filepath string for file to import.
     * @param ignoreLines ArrayList of lines of file to ignore.
     */
    public void importAirports(String filePath, ArrayList<Integer> ignoreLines) throws IncompatibleFileException, FileFormatException {
        AirportRW stream = new AirportRW(filePath);
        ArrayList<Airport> airportList = stream.readAirports(ignoreLines);
        for (Airport airport : airportList) {
            model.addAirport(airport);
        }
        model.save();
    }

    /**
     * Takes a filepath and imports all airports from the file into model.
     * @param filePath Filepath string for file to import.
     */
    public void importAirports(String filePath)
            throws IncompatibleFileException, FileFormatException {
        importAirports(filePath, new ArrayList<>());
    }
}
