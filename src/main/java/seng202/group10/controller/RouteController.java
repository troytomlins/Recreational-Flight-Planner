package seng202.group10.controller;

import seng202.group10.model.*;

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
        RouteRW stream = new RouteRW(filePath);
        ArrayList<Route> routeList = stream.readRoutes();
        for (Route route : routeList) {
            model.addRoute(route);
        }
    }
}