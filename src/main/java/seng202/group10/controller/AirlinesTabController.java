package seng202.group10.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.AirlineController;
import seng202.group10.controller.RouteController;
import seng202.group10.controller.ViewController;
import seng202.group10.controller.filters.AirlineFilters;
import seng202.group10.controller.filters.AirportFilters;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.io.IOException;
import java.util.ArrayList;

public class AirlinesTabController {

    private ViewController mainController;
    @FXML private TableView<Airline> airlineTable;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn aliasCol;
    @FXML private TableColumn iataCol;
    @FXML private TableColumn icaoCol;
    @FXML private TableColumn callsignCol;
    @FXML private TableColumn countryCol;
    @FXML private TextField nameFilterField;
    @FXML private TextField aliasFilterField;
    @FXML private TextField countryFilterField;

    /**
     * Injects main view controller into this controller
     * @param controller View controller to inject
     */
    public void injectMainController(ViewController controller) {
        this.mainController = controller;
    }

    /**
     * Shows import airlines menu screen
     */
    public void importAirlines() {

        // Pick file
        String filepath = mainController.showFileExplorer();

        // Check if a file was chosen
        if (filepath != null) {

            // Import file
            AirlineController controller = mainController.controllerFacade.getAirlineController();
            try {
                controller.importAirlines(filepath);
            } catch (IncompatibleFileException | IOException e) {
                e.printStackTrace();
            }

            // Update table
            updateTable();

        }
    }

    /**
     * Sets data for airline table in GUI
     */
    public void updateTable() {
        AirlineController airlineController = mainController.controllerFacade.getAirlineController();
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

        ArrayList<Airline> data = airlineController.getAirlines();
        airlineTable.setItems(FXCollections.observableList(data));
    }

    public void applyAirlineFilters(){
        AirlineFilters filter = new AirlineFilters();
        AirlineController airlines = new AirlineController();
        ArrayList<Airline> data = new ArrayList<Airline>();
        data = filter.filterByAll(airlines.getAirlines(), nameFilterField.getText(), aliasFilterField.getText(), countryFilterField.getText());
        updateTable();
    }
}
