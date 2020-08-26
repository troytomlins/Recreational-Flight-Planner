package seng202.group10.controller;

import seng202.group10.model.Airport;
import seng202.group10.model.AirportModel;
import seng202.group10.model.IncompatibleFileException;

import java.io.BufferedReader;
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
    public void importAirports(String filePath) throws IncompatibleFileException, IOException {

        // Initialise file reader and string row variable
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Iterate through file reading each line
        while ((row = csvReader.readLine()) != null) {

            // Get data points from row
            String[] data = row.split(",");
            if (data.length != 12) {
                throw new IncompatibleFileException();
            }

            // Get corresponding values from the value list
            String name = data[1];
            String city = data[2];
            String country = data[3];
            String iata= data[4];
            String icao = data[5];
            double latitude = Double.parseDouble(data[6]);
            double longitude = Double.parseDouble(data[7]);
            float altitude = Float.parseFloat(data[8]);
            float timezone = Float.parseFloat(data[9]);
            String dstType = data[10];
            String tzDatabase = data[11];

            // Create airport and add to model
            Airport airport = new Airport(name, city, country, iata, icao, latitude, longitude, altitude, timezone, dstType, tzDatabase);
            model.addAirport(airport);
        }

        // Close reader
        csvReader.close();
    }
}
