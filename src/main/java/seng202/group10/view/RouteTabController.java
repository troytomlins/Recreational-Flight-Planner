package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.AirlineController;
import seng202.group10.controller.RouteController;
import seng202.group10.controller.filters.RouteFilters;
import seng202.group10.model.Airline;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.util.ArrayList;

/**
 * Controller for the airlines tab
 * @author Tom Rizzi
 */
public class RouteTabController {

    // FXML ID things
    @FXML public TableView<Route> routeTable;
    @FXML public TableColumn<Airline, String> airlineCodeCol;
    @FXML public TableColumn<Airline, String> srcAirportCodeCol;
    @FXML public TableColumn<Airline, String> destAirportCodeCol;
    @FXML public TableColumn<Airline, Integer> stopsCol;
    @FXML private TextField airlineCodeFilterField;
    @FXML private TextField srcAirportCodeFilterField;
    @FXML private TextField destAirportCodeFilterField;
    @FXML private TextField numStopsFilterField;
    private ViewController mainController;

    /**
     * Injects main view controller into this controller
     * @param controller View controller to inject
     */
    public void injectMainController(ViewController controller) {
        this.mainController = controller;
    }

    /**
     * Opens file explorer for user to select a file
     * once a file is selected, import it to the controller
     * Once imported, update the table
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
                // Update table
                updateTable(controller.getRoutes());
                // Show success message
                mainController.showInfoWindow("Successfully imported route data");

            } catch (IncompatibleFileException e) {
                mainController.showIncompatibleFileError(e);

            } catch (FileFormatException e) {
                Boolean tryAgain = mainController.showFileFormatError(e);
                if (tryAgain) {

                    // Try import data again, this time ignoring error lines
                    try {
                        controller.importRoutes(filepath, e.getLines());
                    } catch (IncompatibleFileException | FileFormatException err) {
                        mainController.showErrorWindow(err.getMessage());
                    }

                    // Update table
                    ArrayList<Route> data = controller.getRoutes();
                    updateTable(data);

                    // Show success message
                    mainController.showInfoWindow("Successfully imported route data");
                }
            }
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
    // TODO write error checking for filters making sure data is loaded.

    /**
     * Apply the selected/typed filters to the data, update the shown table
     */
    public void applyRouteFilters() {
        RouteFilters filter = new RouteFilters();
        filter.addFilter("airlineCode", airlineCodeFilterField.getText());
        filter.addFilter("sourceAirportCode", srcAirportCodeFilterField.getText());
        filter.addFilter("destinationAirportCode", destAirportCodeFilterField.getText());
        filter.addFilter("stops", numStopsFilterField.getText());
        ArrayList<Route> data = filter.applyFilters();
        updateTable(data);
    }

    /**
     * Exports data to the specified filepath
     * @param filepath Filepath to save the data to
     */
    public void exportData(String filepath) {
        RouteController controller = mainController.controllerFacade.getRouteController();
        controller.writeRoutes(filepath);
    }

    /**
     * Opens a file explorer window to select where to save it to as a csv.
     * Then exports the data to the specified filepath
     */
    public void exportDataCsv() {
        String filepath = mainController.showFileWriterCsv();
        if (filepath != null) {
            exportData(filepath);
        }
    }

    /**
     * Opens a file explorer window to select where to save it to as a dat.
     * Then exports the data to the specified filepath
     */
    public void exportDataDat() {
        String filepath = mainController.showFileWriterDat();
        if (filepath != null) {
            exportData(filepath);
        }
    }

    /**
     * Opens a file explorer window to select where to save it to as a txt.
     * Then exports the data to the specified filepath
     */
    public void exportDataTxt() {
        String filepath = mainController.showFileWriterTxt();
        if (filepath != null) {
            exportData(filepath);
        }
    }
}
