package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.model.*;

import java.io.File;
import java.util.*;

/**
 * View Controller
 * Handles the file explorer, adding and removing markers (NOT IN RELEASE 1!)
 *
 * @author Niko Tainui
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
    @FXML
    public TabPane mainTabPane;

    public WebEngine webEngine;

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

    /**
     * Shows the file saving window for .txt files
     * @return File path
     */
    public String showFileWriterTxt() {
        return showFileWriter("TXT files (*.txt)", "*.txt");
    }

    /**
     * Shows the file saving window for .csv files
     * @return File path
     */
    public String showFileWriterCsv() {
        return showFileWriter("CSV files (*.csv)", "*.csv");
    }

    /**
     * Shows the file saving window for .dat files
     * @return File path
     */
    public String showFileWriterDat() {
        return showFileWriter("DAT files (*.dat)", "*.dat");
    }

    /**
     * Default show file write window method, uses csv files
     * @return File path
     */
    public String showFileWriter() {
        return showFileWriterCsv();
    }

    /**
     * Shows file explorer for writing files.
     * @param extensionString File extension representation string
     * @param extensionRegex File extension regex expression
     * @return String representation of filepath
     */
    public String showFileWriter(String extensionString, String extensionRegex) {
        // Create a new file chooser stage
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        // Add extensions checker
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extensionString, extensionRegex);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file window
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
     */
    public void remakeLocationBoxes() {
        locationsPane.getChildren().clear();
        ArrayList<FlightPoint> flightPoints = flight.getFlightPoints();
        for (int i = 0; i < flightPoints.size(); i ++) {

            FlightPoint point = flightPoints.get(i);
            point.id = Integer.toString(i);
            newLocationBox(i + 1, point);
        }
    }

    /**
     * Add a new marker into the plan flight section
     *
     * @param id  - id of marker
     * @param lat - position latitude
     * @param lng - position longitude
     */
    public void newMarker(String id, double lat, double lng) {
        flight.addPoint(new FlightPoint("NA", id, 0, lat, lng));
        remakeLocationBoxes();
    }

    /**
     * Remove the location box with id
     * @param id id of marker to remove
     */
    public void removeMarker(String id) {
        ArrayList<FlightPoint> flightPoints = flight.getFlightPoints();
        flightPoints.removeIf(flightPoint -> flightPoint.id.equals(id));
        remakeLocationBoxes();
    }

    public void markerChange(String id, double newLat, double newLng) {
        ArrayList<FlightPoint> flightPoints = flight.getFlightPoints();
        for (FlightPoint point : flightPoints) {
            if (point.id.equals(id)) {
                point.latitude = newLat;
                point.longitude = newLng;
            }
        }
        remakeLocationBoxes();
    }


//    /**
//     * Edit the location box at row
//     * @param row row to edit
//     * @param newId
//     * @param newLat
//     * @param newLng
//     */
//    public void editLocationBox(int row, String newId, double newLat, double newLng) {
//        ObservableList<Node> children = locationsPane.getChildren();
//        GridPane pane = (GridPane) children.get(row);
//        ((Label) pane.getChildren().get(0)).setText(newId + " " + newLat +" " + newLng);
//    }

    /**
     * Make a new box to show the marker location
     * @param row - row index to place it
     */
    private void newLocationBox(int row, FlightPoint point) {

        //Add the thing
        LocationBox box = new LocationBox(point);
        locationsPane.add(box.pane, 0, row);
    }

    /**
     * Clear all the markers
     */
    public void clearMarkers() {
        flight = new Flight();
        locationsPane.getChildren().clear();
        webEngine.executeScript("removeAllMarkers()");
    }

    /**
     * writes flight to file overwrites existing global flight
     */
    public void saveFlight() {
        flight.setAircraft((Aircraft) aircraftSelector.getValue());
        System.out.println(flight.getAircraftName());
        String filepath = new String();
        FlightModel model= new FlightModel();
        filepath = showFileWriter();
        FlightRW write = new FlightRW(filepath,filepath);
        model.addFlight(flight);
        write.writeFlight(flight);
    }
}


class LocationBox {
    int height = 100;

    FlightPoint flightPoint;

    GridPane pane;
    Label label;
    TextField altitudeField;
    Button button;

    LocationBox(FlightPoint point) {
        flightPoint = point;
        // Making box
        pane = new GridPane();
        button = new Button("Set Altitude");
        label = new Label(point.id + " " + point.latitude + " " + point.longitude);   // ID and position
        pane.add(label, 0, 0);
        altitudeField = new TextField("0");                // Altitude text box
        pane.add(altitudeField, 0, 1);
        pane.add(button, 0, 2);

        // Setting height
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        pane.setPrefHeight(height);

        // Padding
        javafx.geometry.Insets inset = new javafx.geometry.Insets(5, 5, 5, 5);
        altitudeField.setPadding(inset);
        label.setPadding(inset);

//         Altitude change listener
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String input = altitudeField.getText();
                // TODO check input is number
                flightPoint.altitude = Float.parseFloat(input);
            }
        });
    }
}
