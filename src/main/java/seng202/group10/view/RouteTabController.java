package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.RouteController;
import seng202.group10.controller.filters.RouteFilters;
import seng202.group10.model.Airline;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.io.IOException;
import java.util.ArrayList;

public class RouteTabController {

    @FXML public TableView routeTable;
    @FXML public TableColumn airlineCodeCol;
    @FXML public TableColumn srcAirportCodeCol;
    @FXML public TableColumn destAirportCodeCol;
    @FXML public TableColumn stopsCol;
    private ViewController mainController;
    @FXML private TextField airlineCodeFilterField;
    @FXML private TextField srcAirportCodeFilterField;
    @FXML private TextField destAirportCodeFilterField;
    @FXML private TextField numStopsFilterField;

    /**
     * Injects main view controller into this controller
     * @param controller View controller to inject
     */
    public void injectMainController(ViewController controller) {
        this.mainController = controller;
    }

    /**
     * Import routes from file using file explorer window and update table
     */
    public void importRoutes() {
        // Pick file
        String filepath = mainController.showFileExplorer();

        // Check if a file was chosen
        if (filepath != null) {

            // Import file
            RouteController controller = mainController.controllerFacade.getRouteController();
            try {
                controller.importRoutes(filepath);
            } catch (IncompatibleFileException | IOException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("File Not Compatible");
                errorAlert.showAndWait();
            }

            // Update table
            ArrayList<Route> data = controller.getRoutes();
            updateTable(data);
        }
    }

    /**
     * Update routes table with data
     * @param data List of routes to fill table with
     */
    public void updateTable(ArrayList<Route> data) {
        routeTable.setEditable(true);

        airlineCodeCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("airlineCode"));
        srcAirportCodeCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("sourceAirportCode"));


        destAirportCodeCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("destinationAirportCode"));
        stopsCol.setCellValueFactory(new PropertyValueFactory<Airline, Integer>("stops"));
        routeTable.setItems(FXCollections.observableList(data));
    }

    //TODO write error checking for filters making sure data is loaded.

    public void applyRouteFilters() {
        RouteFilters filter = new RouteFilters();
        RouteController routes = new RouteController();
        ArrayList<Route> data = new ArrayList<Route>();
        data = filter.filterByAll(routes.getRoutes(), airlineCodeFilterField.getText(), srcAirportCodeFilterField.getText(), destAirportCodeFilterField.getText(), Integer.parseInt(numStopsFilterField.getText()));
        updateTable(data);
    }
}
