package seng202.group10.view;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.model.*;
import seng202.group10.view.AirlinesTabController;
import seng202.group10.view.AirportTabController;
import seng202.group10.view.RouteTabController;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * View Controller
 * Handles the file explorer, adding and removing markers (NOT IN RELEASE 1!)
 *
 * @author Tom Rizzi
 * @author Johnny Howe
 */
public class ViewController {

    // Things with FXML ids
    @FXML
    public RouteTabController routeTabController;
    @FXML
    private AirportTabController airportTabController;
    @FXML
    private AirlinesTabController airlineTabController;
    @FXML
    private AircraftTabController aircraftTabController;
    @FXML
    private FlightTabController flightTabController;
    @FXML
    private MenuButton dropdownView;
    @FXML
    private MenuItem importAirlinesMenuItem;
    @FXML
    private GridPane locationsPane;
    @FXML
    private ComboBox aircraftSelector;
    @FXML
    private TextField filename;

    public Stage stage;
    public ControllerFacade controllerFacade;
    public Flight flight = new Flight();

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    /**
     * Initialize the routes, airports, airlines and aircraft controllers
     */
    @FXML
    private void initialize() {
        routeTabController.injectMainController(this);
        airportTabController.injectMainController(this);
        airlineTabController.injectMainController(this);
        aircraftTabController.injectMainController(this);
        flightTabController.injectController(this);
        controllerFacade = new ControllerFacade();
        updateAllTables();
        listAircraft();
    }

    /**
     * Populates all tables with data
     */
    private void updateAllTables() {
        airportTabController.updateTable(controllerFacade.getAirportController().getAirports());
        routeTabController.updateTable(controllerFacade.getRouteController().getRoutes());
        airlineTabController.updateTable(controllerFacade.getAirlineController().getAirlines());
        aircraftTabController.updateTable(controllerFacade.getAircraftController().getAircraft());
        flightTabController.updateTable(controllerFacade.getFlightController().getFlights());
    }

    /**
     * Creates a file explorer window, returns the filepath to the file picked.
     * Returns null if no file was expected
     *
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

    public String showFileWriter() {
        // Create a new file chooser stage
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);

        // Create filepath
        String filepath = null;
        if (file != null) {
            filepath = file.getPath();
        }

        return filepath;
    }
    /**
     * Shows an information window with provided message
     *
     * @param message Message to display on window
     */
    public void showInfoWindow(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }

    /**
     * Shows an error window with provided message
     *
     * @param message Message to display on window
     */
    public void showErrorWindow(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }

    /**
     * Shows the error window for an incompatible file
     *
     * @param e Exception to display
     */
    public void showIncompatibleFileError(IncompatibleFileException e) {
        showErrorWindow(e.getMessage());
    }

    /**
     * Shows an error window for a file format exception,
     * then waits for users choice of either import lines that are not causing errors or cancel
     *
     * @param e Exception to display
     * @return Boolean value, representing weather to import non-erroneous lines
     */
    public Boolean showFileFormatError(FileFormatException e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("File Format Error");
        String message = String.format("%d line%s with incorrect format in file '%s'!",
                e.getLines().size(), e.getLines().size() > 1 ? "s" : "", e.getFileName());
        alert.setHeaderText(message);
        alert.setContentText("Do you want to ignore these lines and import the rest?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void listAircraft() {
        AircraftModel aircraftmodel = new AircraftModel();
        ArrayList<Aircraft> aircraft = new ArrayList<>();
        aircraft = aircraftmodel.getAircraftList();
        ObservableList observableAircraft = FXCollections.observableArrayList(aircraft);
        aircraftSelector.setItems(observableAircraft);
    }

    /**
     * (re)set all the markers in the window
     * @param labels
     * @param lats
     * @param lngs
     */
    public void setMarkers(ArrayList<String> labels, ArrayList<Float> lats, ArrayList<Float> lngs) {
        locationsPane.getChildren().clear();    // Delete old
        for (int i = 0; i < labels.size(); i++) {
            newMarker(labels.get(i), lats.get(i), lngs.get(i));
        }
    }

    private int numMarkers = 1;     // How many markers do we currently have?

    /**
     * Add a new marker into the plan flight section
     *
     * @param id  - id of marker
     * @param lat - position latitude
     * @param lng - position longitude
     */
    public void newMarker(String id, double lat, double lng) {
        newLocationBox(id, numMarkers, lat, lng);
        numMarkers += 1;
    }

    /**
     * Make a new box to show the marker location
     *
     * @param id  - id of marker
     * @param row - row index to place it
     * @param lat - position latitude
     * @param lng - position longitude
     */
    private void newLocationBox(String id, int row, double lat, double lng) {
        //Make the thing
        int height = 100;

        // Making box
        GridPane pane = new GridPane();
        Button setAltitude = new Button("Set Altitude");
        Label latLng = new Label(id + " " + lat + " " + lng);   // ID and position
        pane.add(latLng, 0, 0);
        TextField altitude = new TextField("0");                // Altitude text box
        pane.add(altitude, 0, 1);
        pane.add(setAltitude, 0, 2);

        // Setting height
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        pane.setPrefHeight(height);

        // Padding
        javafx.geometry.Insets inset = new javafx.geometry.Insets(5, 5, 5, 5);
        altitude.setPadding(inset);
        latLng.setPadding(inset);

        //Add the thing
        locationsPane.add(pane, 0, row);
        if (setAltitude.isPressed()) {
            try {
                FlightPoint point = new FlightPoint(id, "NA", lat, lng, Double.parseDouble(altitude.getText()));
                flight.addPoint(point);
                setAltitude.disarm();
            } catch (NumberFormatException e) {
                showErrorWindow("Altitude field not valid");
            }
        }
    }

    public void saveFlight() {
        String filepath = new String();
        locationsPane.getChildren().clear();
        FlightModel model= new FlightModel();
        filepath = showFileWriter();
        FlightRW write = new FlightRW(filepath);
        model.addFlight(flight);
        write.writeFlight(flight);
        flight = new Flight();
    }
}
