package seng202.group10.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;

import java.io.File;
import java.util.Optional;

/**
 * View Controller
 * Handles the file explorer, adding and removing markers (NOT IN RELEASE 1!)
 *
 * @author Tom Rizzi
 * @author Johnny Howe
 */
public class ViewController {

    // Things with FXML ids
    @FXML public RouteTabController routeTabController;
    @FXML private AirportTabController airportTabController;
    @FXML private AirlinesTabController airlineTabController;
    @FXML private AircraftTabController aircraftTabController;
    @FXML private FlightTabController flightTabController;
    @FXML private MenuButton dropdownView;
    @FXML private MenuItem importAirlinesMenuItem;
    @FXML private GridPane locationsPane;

    public Stage stage;
    public ControllerFacade controllerFacade;

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    /**
     * Initialize the routes, airports, airlines and aircraft controllers
     */
    @FXML private void initialize() {
        routeTabController.injectMainController(this);
        airportTabController.injectMainController(this);
        airlineTabController.injectMainController(this);
        aircraftTabController.injectMainController(this);
        flightTabController.injectController(this);
        controllerFacade = new ControllerFacade();
        updateAllTables();
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
     * Shows an information window with provided message
     * @param message Message to display on window
     */
    public void showInfoWindow(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }

    /**
     * Shows an error window with provided message
     * @param message Message to display on window
     */
    public void showErrorWindow(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }

    /**
     * Shows the error window for an incompatible file
     * @param e Exception to display
     */
    public void showIncompatibleFileError(IncompatibleFileException e) {
        showErrorWindow(e.getMessage());
    }

    /**
     * Shows an error window for a file format exception,
     * then waits for users choice of either import lines that are not causing errors or cancel
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

    private int numMarkers = 0;     // How many markers do we currently have?
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
