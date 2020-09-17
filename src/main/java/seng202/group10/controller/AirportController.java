package seng202.group10.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import seng202.group10.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AirportController {

    /**
     * Model
     */
    private AirportModel model;

    /**
     * Constructor
     */
    public AirportController(AirportModel model) {
        this.model = model;
    }

    public AirportController() {
        this.model = new AirportModel();
    }

    /**
     * Get a list of airports from model
     * @return Arraylist of airports
     */
    public ArrayList<Airport> getAirports() {
        return model.getAirports();
    }

    /**
     * Takes a filepath and imports all airports from the file into model.
     * @param filePath Filepath string for file to import.
     */
    public void importAirports(String filePath, ArrayList<Integer> ignoreLines) throws IncompatibleFileException, FileFormatException {
        AirportRW stream = new AirportRW(filePath);
        ArrayList<Airport> airportList = stream.readAirports(ignoreLines);
        for (Airport airport : airportList) {
            model.addAirport(airport);
        }
        model.save();
    }

    public void importAirports(String filePath)
            throws IncompatibleFileException, FileFormatException {
        importAirports(filePath, new ArrayList<>());
    }
}
