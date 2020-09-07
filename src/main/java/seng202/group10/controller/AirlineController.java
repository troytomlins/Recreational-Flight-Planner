package seng202.group10.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import seng202.group10.model.Airline;
import seng202.group10.model.AirlineModel;
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

        // Initialise file reader and string row variable
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Parse each line
        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {
            // Get corresponding values from the value list
            String name = csvRecord.get(1);
            String alias = csvRecord.get(2);
            String iata = csvRecord.get(3);
            String icao = csvRecord.get(4);
            String callsign = csvRecord.get(5);
            String country = csvRecord.get(6);

            // Create airline and add to model
            Airline airline = new Airline(name, alias, iata, icao, callsign, country);
            model.addAirline(airline);
        }

        // Close reader
        csvReader.close();
    }


}
