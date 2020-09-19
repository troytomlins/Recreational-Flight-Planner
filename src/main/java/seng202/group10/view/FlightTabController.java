package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.controller.FlightController;
import seng202.group10.model.Flight;
import seng202.group10.model.IncompatibleFileException;

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
    }

    /**
     * TODO
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
     * Bring up menu to create a new flight
     * @param event
     */
    public void createFlightWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createFlight.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Create New Aircraft");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                (mainController.stage).getScene().getWindow() );
        CreateFlightWindow controller = loader.getController();
        controller.injectStage(stage, this);
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
