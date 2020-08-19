package seng202.group10.controller;

import seng202.group10.model.Airline;
import seng202.group10.model.AirlineModel;
import seng202.group10.model.IncompatibleFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        // Initialise file reader and string row variable
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Iterate through file reading each line
        while ((row = csvReader.readLine()) != null) {

            // Get data points from row
            String[] data = row.split(",");
            if (data.length != 8) {
                throw new IncompatibleFileException();
            }

            // Get corresponding values from the value list
            String name = data[1];
            String alias = data[2];
            String iata = data[3];
            String icao = data[4];
            String callsign = data[5];
            String country = data[6];

            // Create airline and add to model
            Airline airline = new Airline(name, alias, iata, icao, callsign, country);
            model.addAirline(airline);
        }

        // Close reader
        csvReader.close();
    }


}
