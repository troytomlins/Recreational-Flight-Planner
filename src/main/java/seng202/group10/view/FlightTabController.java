package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.FlightController;
import seng202.group10.model.Aircraft;
import seng202.group10.model.Airline;
import seng202.group10.model.Flight;
import seng202.group10.model.IncompatibleFileException;

import java.io.IOException;
import java.util.ArrayList;

public class FlightTabController {


    @FXML public TableColumn aircraftCol;
    @FXML public TableColumn startLatCol;
    @FXML public TableColumn destLatCol;
    @FXML public TableColumn distCol;
    @FXML private TableView flightsTable;


    public ViewController mainController;

    /**
     * Injects main controller into flight tab
     */
    public void injectController(ViewController controller) {
        this.mainController = controller;
    }

    /**
     *
     * @param actionEvent
     */
    public void importFlights(ActionEvent actionEvent) throws IOException, IncompatibleFileException {
        FlightController flightController = mainController.controllerFacade.getFlightController();
        String fileString = mainController.showFileExplorer();
        try {
            flightController.importFlight(fileString);
        } catch (IncompatibleFileException e) {
            // TODO Implement error
            return;
        }
        updateTable();
    }

    /**
     * Updates flight table with current model data
     */
    public void updateTable() {
        ArrayList<Flight> flights = mainController.controllerFacade.getFlightController().getFlights();

        flightsTable.setEditable(true);
        aircraftCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("aircraftName"));
        startLatCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("startLatitudeString"));
        destLatCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("destLatitudeString"));
        distCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("totalDistance"));

        flightsTable.setItems(FXCollections.observableList(flights));

    }
}
