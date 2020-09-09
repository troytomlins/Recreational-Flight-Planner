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
    public void importAirports(String filePath) throws IncompatibleFileException, IOException{
        AirportRW stream = new AirportRW(filePath);
        ArrayList<Airport> airportList = stream.readAirports();
        for (Airport airport : airportList) {
            model.addAirport(airport);
        }
    }

    /**
     * Takes a filepath and imports all airports from the file into model.
     * @param filePath Filepath string for file to import.
     */
    public void importAirportsOld(String filePath) throws IncompatibleFileException, IOException {

        // Initialise file reader and string row variable
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Parse each line
        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {
            try {

                // Get corresponding values from the csv record
                String name = csvRecord.get(1);
                String city = csvRecord.get(2);
                String country = csvRecord.get(3);
                String iata = csvRecord.get(4);
                String icao = csvRecord.get(5);
                double latitude = Double.parseDouble(csvRecord.get(6));
                double longitude = Double.parseDouble(csvRecord.get(7));
                float altitude = Float.parseFloat(csvRecord.get(8));
                float timezone = Float.parseFloat(csvRecord.get(9));
                String dstType = csvRecord.get(10);
                String tzDatabase = csvRecord.get(11);

                // Create airline and add to model
                Airport airport = new Airport(name, city, country, iata, icao, latitude, longitude, altitude, timezone, dstType, tzDatabase);
                model.addAirport(airport);
            } catch (Exception e) {
                throw new IncompatibleFileException();
            }
        }

        // Close reader
        csvReader.close();

    }
}
