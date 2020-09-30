package seng202.group10.controller;

import seng202.group10.model.*;

import java.util.ArrayList;

/**
 * Controller class for airlines.
 *
 */
public class AirlineController {

    /**
     * Model
     */
    private AirlineModel model;

    public AirlineController(AirlineModel model) {
        this.model = model;
    }

    public AirlineController() {
        this.model = new AirlineModel();
    }

    /**
     * Get a list of airlines from model
     * @return Arraylist of airlines
     */
    public ArrayList<Airline> getAirlines() {
        return model.getAirlines();
    }

    /**
     * Takes a filepath and imports all airlines from the file into model.
     * @param filePath Filepath string for file to import.
     * @throws IncompatibleFileException when a non csv file is given
     * @throws FileFormatException when file is incorrectly formatted
     */
    public void importAirlines(String filePath) throws IncompatibleFileException, FileFormatException {
        importAirlines(filePath, new ArrayList<>());
    }

    /**
     * Takes a filepath and imports all airlines from the file into model.
     * @param filePath Filepath string for file to import.
     * @param lines List of line indices to ignore when importing
     * @throws IncompatibleFileException when a non csv file is given
     * @throws FileFormatException when file is incorrectly formatted
     */
    public void importAirlines(String filePath, ArrayList<Integer> lines) throws IncompatibleFileException, FileFormatException {
        AirlineRW stream = new AirlineRW(filePath);
        ArrayList<Airline> airlines = stream.readAirlines(lines);
        for (Airline airline : airlines) {
            model.addAirline(airline);
        }
        model.save();
    }

    /**
     * Writes airports to a file using RW class to specified filepath
     * @param filepath Filepath to write data file to
     */
    public void writeAirports(String filepath) {
        AirlineRW stream = new AirlineRW();
        stream.setOutFileName(filepath);
        stream.writeAirlines(model.getAirlines());
    }
}
