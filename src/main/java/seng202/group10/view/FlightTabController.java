package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.FlightController;
import seng202.group10.model.Flight;
import seng202.group10.model.IncompatibleFileException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Controller for the flight tab
 */
public class FlightTabController {

    @FXML public TableColumn<Flight, String> aircraftCol;
    @FXML public TableColumn<Flight, String> startCoordCol;
    @FXML public TableColumn<Flight, String> destCoordCol;
    @FXML public TableColumn<Flight, String> distCol;
    @FXML public TableColumn<Flight, String> legCountCol;
    @FXML private TableView<Flight> flightsTable;


    public ViewController mainController;

    /**
     * Injects main controller into flight tab
     * @param controller - controller that is used in the flight tab
     */
    public void injectController(ViewController controller) {
        this.mainController = controller;
    }

    /**
     * @param actionEvent - importing flights brings up file-manager
     * @throws IOException - thrown when a flight is unable to be imported
     * @throws IncompatibleFileException - thrown when a incompatible file is tried to be imported
     */
    public void importFlights(ActionEvent actionEvent) throws IOException, IncompatibleFileException {
        FlightController flightController = mainController.controllerFacade.getFlightController();
        String fileString = mainController.showFileExplorer();
        try {
            flightController.importFlight(fileString);
        } catch (IncompatibleFileException e) {
            mainController.showErrorWindow(e.getMessage());
            return;
        }
        updateTable(flightController.getFlights());
    }

    /**
     * Updates flight table with current model data
     * @param flights - flights to update table with
     */
    public void updateTable(ArrayList<Flight> flights) {

        flightsTable.setEditable(true);
        aircraftCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("aircraftName"));
        startCoordCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("startCoordString"));
        destCoordCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("destCoordString"));
        distCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("totalDistance"));
        legCountCol.setCellValueFactory(new PropertyValueFactory<>("legCount"));

        flightsTable.setItems(FXCollections.observableList(flights));

    }
}
