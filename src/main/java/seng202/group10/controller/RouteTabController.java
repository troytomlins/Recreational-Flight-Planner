package seng202.group10.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.io.IOException;
import java.util.ArrayList;

public class RouteTabController {

    @FXML public TableView routeTable;
    @FXML public TableColumn nameCol;
    @FXML private ViewController mainController;

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
    private void updateTable(ArrayList<Route> data) {
        routeTable.setEditable(true);

        nameCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("name"));
        routeTable.setItems(FXCollections.observableList(data));
    }
}
