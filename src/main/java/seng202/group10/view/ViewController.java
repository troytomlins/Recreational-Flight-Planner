package seng202.group10.view;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.model.*;
import seng202.group10.view.AirlinesTabController;
import seng202.group10.view.AirportTabController;
import seng202.group10.view.RouteTabController;

import java.awt.*;
import java.io.File;
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
    @FXML public RouteTabController routeTabController;
    @FXML private AirportTabController airportTabController;
    @FXML private AirlinesTabController airlineTabController;
    @FXML private AircraftTabController aircraftTabController;
    @FXML private FlightTabController flightTabController;
    @FXML private MenuButton dropdownView;
    @FXML private MenuItem importAirlinesMenuItem;
    @FXML private GridPane locationsPane;
    @FXML private ComboBox aircraftSelector;

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
        listAircraft();
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

    public void listAircraft() {
        AircraftModel aircraftmodel = new AircraftModel();
        ArrayList<Aircraft> aircraft = new ArrayList<>();
        ObservableList observableAircraft = new ObservableList() {
            @Override
            public void addListener(ListChangeListener listChangeListener) {

            }

            @Override
            public void removeListener(ListChangeListener listChangeListener) {

            }

            @Override
            public boolean addAll(Object[] objects) {
                return false;
            }

            @Override
            public boolean setAll(Object[] objects) {
                return false;
            }

            @Override
            public boolean setAll(Collection collection) {
                return false;
            }

            @Override
            public boolean removeAll(Object[] objects) {
                return false;
            }

            @Override
            public boolean retainAll(Object[] objects) {
                return false;
            }

            @Override
            public void remove(int i, int i1) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] a) {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Object get(int index) {
                return null;
            }

            @Override
            public Object set(int index, Object element) {
                return null;
            }

            @Override
            public void add(int index, Object element) {

            }

            @Override
            public Object remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator listIterator() {
                return null;
            }

            @Override
            public ListIterator listIterator(int index) {
                return null;
            }

            @Override
            public List subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }
        };
        // problem is will list whole aircraft rather than just the name?
        // probably will
        aircraft = aircraftmodel.getAircraftList();
        observableAircraft = FXCollections.observableArrayList(aircraft);
        System.out.println(observableAircraft);
        aircraftSelector.setItems(observableAircraft);
    }

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


    //public void setUpFlightPlanner() {

    //}
    /**
     * Make a new box to show the marker location
     * @param id - id of marker
     * @param column - column index to place it
     * @param lat - position latitude
     * @param lng - position longitude
     */
    private void newLocationBox(String id, int column, float lat, float lng) {
        //Make the thing
        int height = 100;
        TextField altitude = new TextField("0");
        javafx.geometry.Insets inset = new javafx.geometry.Insets(5,5,5,5);
        altitude.setPadding(inset);
        GridPane pane = new GridPane();
        pane.add(new Label(id + " " + lat + " " + lng), 0, numMarkers+1);
        pane.add(altitude,0,numMarkers+2);
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        pane.setPrefHeight(height);

        //Add the thing
        locationsPane.setGridLinesVisible(true);
        locationsPane.add(pane, 0, column);
    }
}
