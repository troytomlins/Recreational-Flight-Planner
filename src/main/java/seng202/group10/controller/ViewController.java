package seng202.group10.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.IncompatibleFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML public RouteTabController routeTabController;
    @FXML
    private MenuButton dropdownView;
    @FXML
    private TableView<Airline> airlineTable;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn aliasCol;
    @FXML
    private TableColumn iataCol;
    @FXML
    private TableColumn icaoCol;
    @FXML
    private TableColumn callsignCol;
    @FXML
    private TableColumn countryCol;
    @FXML
    private MenuItem importAirlinesMenuItem;
    @FXML
    private AirportTabController airportTabController;
    @FXML
    private GridPane locationsPane;

    private List<Airline> data;
    public ControllerFacade controllerFacade;

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    @FXML private void initialize() {
        routeTabController.injectMainController(this);
        airportTabController.injectMainController(this);
    }

    /**
     * Sets data for airline table in GUI
     */
    public void setAirlineTable() {
        AirlineController airlineController = controllerFacade.getAirlineController();
        airlineTable.setEditable(true);

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("name"));
        aliasCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("alias"));
        icaoCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("icao"));
        callsignCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("callsign"));
        countryCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("country"));
        iataCol.setCellValueFactory(
                new PropertyValueFactory<Airline, String>("iata"));

        data = airlineController.getAirlines();
        airlineTable.setItems(FXCollections.observableList(data));
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

    /**
     * Shows import airlines menu screen
     */
    public void showImportAirlines() {

        // Pick file
        String filepath = showFileExplorer();

        // Check if a file was chosen
        if (filepath != null) {

            // Import file
            AirlineController controller = controllerFacade.getAirlineController();
            try {
                controller.importAirlines(filepath);
            } catch (IncompatibleFileException | IOException e) {
                e.printStackTrace();
            }

            // Update table
            setAirlineTable();

        }
    }

    /**
     * Shows the file explorer, then imports the selected airports file into model.
     */
    public void importAirports() {
        airportTabController.importAirports();
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
