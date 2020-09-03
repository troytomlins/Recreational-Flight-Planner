package seng202.group10.controller;

import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;
import seng202.group10.model.RouteModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RouteController {

    /**
     * Model
     */
    private RouteModel model;

    /**
     * Constructor
     */
    public RouteController(RouteModel model) {
        this.model = model;
    }

    public RouteController() {
        this.model = new RouteModel();
    }

    /**
     * Get a list of routes from model
     * @return Arraylist of routes
     */
    public ArrayList<Route> getRoutes() {
        return model.getRoutes();
    }

    /**
     * Takes a filepath and imports all routes from the file into model.
     * @param filePath Filepath string for file to import.
     */
    public void importRoutes(String filePath) throws IncompatibleFileException, IOException {

        // Initialise file reader and string row variable
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Iterate through file reading each line
        while ((row = csvReader.readLine()) != null) {

            // Get data points from row
            String[] data = row.split(",");
            if (data.length != 9) {
                throw new IncompatibleFileException();
            }

            // Get corresponding values from the value list
            String airlineCode = data[0];
            int airlineId = Integer.parseInt(data[1]);
            String sourceAirportCode = data[2];
            String sourceAirport = data[3];
            String destinationAirportCode = data[4];
            String destinationAirport = data[5];
            int stops = Integer.parseInt(data[7]);
            String equipmentString = data[8];

            // Convert values to the type required for creating a route.
            ArrayList<String> equipment = new ArrayList<String>(Arrays.asList(equipmentString.split(" ")));

            // Create route and add to model
            Route route = new Route(airlineCode, airlineId, sourceAirportCode, sourceAirport, destinationAirportCode, destinationAirport, stops, equipment);
            model.addRoute(route);
        }

        // Close reader
        csvReader.close();
    }

}
