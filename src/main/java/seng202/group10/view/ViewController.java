package seng202.group10.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.view.AirlinesTabController;
import seng202.group10.view.AirportTabController;
import seng202.group10.view.RouteTabController;

import java.io.File;

public class ViewController {

    @FXML public RouteTabController routeTabController;
    @FXML private AirportTabController airportTabController;
    @FXML private AirlinesTabController airlineTabController;
    @FXML private AircraftTabController aircraftTabController;
    @FXML private MenuButton dropdownView;
    @FXML private MenuItem importAirlinesMenuItem;
    @FXML private GridPane locationsPane;

    public Stage stage;
    public ControllerFacade controllerFacade;

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    @FXML private void initialize() {
        routeTabController.injectMainController(this);
        airportTabController.injectMainController(this);
        airlineTabController.injectMainController(this);
        aircraftTabController.injectMainController(this);
    }

    /**
     * Creates a file explorer window, returns the filepath to the file picked.
     * Returns null if no file was expected
     * @return file path string of file selected, or null if none selected.
     */
    public String showFileExplorer() {
        // Create a new file chooser stage
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        // Create filepath
        String filepath = null;
        if (file != null) {
            filepath = file.getPath();
        }

        return filepath;
    }

    private int numMarkers = 0;
    /**
     * Add a new marker into the plan flight section
     * @param id - id of marker
     * @param lat - position latitude
     * @param lng - position longitude
     */
    public void newMarker(String id, float lat, float lng) {
        newLocationBox(id, numMarkers, lat, lng);
        numMarkers += 1;
    }

    /**
     * Make a new box to show the marker location
     * @param id - id of marker
     * @param column - column index to place it
     * @param lat - position latitude
     * @param lng - position longitude
     */
    private void newLocationBox(String id, int column, float lat, float lng) {
        // Make the thing
        int height = 100;
        GridPane pane = new GridPane();
        pane.add(new Label(id + " " + lat + " " + lng), 0, numMarkers);
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        pane.setPrefHeight(height);

        // Add the thing
        locationsPane.setGridLinesVisible(true);
        locationsPane.add(pane, 0, column);
    }
}
