package seng202.group10.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import seng202.group10.model.Airline;
import seng202.group10.model.AirlineModel;
import seng202.group10.model.AirlineRW;
import seng202.group10.model.IncompatibleFileException;

import java.io.*;
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

    /**
     * Constructor
     */
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
     */
    public void importAirlines(String filePath) throws IncompatibleFileException, IOException {
        AirlineRW stream = new AirlineRW(filePath);
        ArrayList<Airline> airlines = stream.readAirlines();
        for (Airline airline : airlines) {
            model.addAirline(airline);
        }
        model.save();
    }

}
