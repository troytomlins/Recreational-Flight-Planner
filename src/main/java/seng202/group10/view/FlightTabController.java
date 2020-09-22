package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.controller.FlightController;
import seng202.group10.model.Flight;
import seng202.group10.model.IncompatibleFileException;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

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
     */
    public void injectController(ViewController controller) {

        this.mainController = controller;
        // Set Click
        flightsTable.setRowFactory( tv -> {
            TableRow<Flight> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Flight rowData = row.getItem();
                    try {
                        showIndividualFlightWindow(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
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
            mainController.showErrorWindow(e.getMessage());
            return;
        }
        updateTable(flightController.getFlights());
    }

    /**
     * Creates a window showing an individual flight
     * @param flight Flight to show on the window
     * @throws IOException
     */
    public void showIndividualFlightWindow(Flight flight) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewIndividualFlight.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Flight");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                (mainController.stage).getScene().getWindow() );
        IndividualFlightWindow controller = loader.getController();
        controller.initialize(mainController, stage, flight);
        stage.show();
    }

    /**
     * Updates flight table with current model data
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
