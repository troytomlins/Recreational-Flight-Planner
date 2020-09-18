package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.AirlineController;
import seng202.group10.controller.filters.AirlineFilters;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the airlines tab
 * @author Tom Rizzi
 */
public class AirlinesTabController {

    // FXML things
    private ViewController mainController;
    @FXML private TableView<Airline> airlineTable;
    @FXML private TableColumn<Airline, String> nameCol;
    @FXML private TableColumn<Airline, String> aliasCol;
    @FXML private TableColumn<Airline, String> iataCol;
    @FXML private TableColumn<Airline, String> icaoCol;
    @FXML private TableColumn<Airline, String> callsignCol;
    @FXML private TableColumn<Airline, String> countryCol;
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
     * Opens file explorer for user to select a file
     * once a file is selected, import it to the controller
     * Once imported, update the table
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
                // Update table
                updateTable(controller.getAirlines());
                // Show success message
                mainController.showInfoWindow("Successfully imported airline data");

            } catch (IncompatibleFileException e) {
                mainController.showIncompatibleFileError(e);

            } catch (FileFormatException e) {
                Boolean tryAgain = mainController.showFileFormatError(e);
                if (tryAgain) {

                    // Try import data again, this time ignoring error lines
                    try {
                        controller.importAirlines(filepath, e.getLines());
                    } catch (IncompatibleFileException | FileFormatException err) {
                        mainController.showErrorWindow(err.getMessage());
                    }

                    // Update table
                    ArrayList<Airline> data = controller.getAirlines();
                    updateTable(data);

                    // Show success message
                    mainController.showInfoWindow("Successfully imported airline data");
                }
            }


        }
    }

    /**
     * Sets data for airline table in GUI according to airlineController.getAirlines
     */
    public void updateTable(ArrayList<Airline> data) {
        airlineTable.setEditable(true);

        nameCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("name"));
        aliasCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("alias"));
        icaoCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("icao"));
        callsignCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("callsign"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("country"));
        iataCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("iata"));

        airlineTable.setItems(FXCollections.observableList(data));
    }

    /**
     * Apply the selected/typed filters to the data, update the shown table
     */
    public void applyAirlineFilters(){
        AirlineFilters filter = new AirlineFilters();
        AirlineController airlines = new AirlineController();
        ArrayList<Airline> data = new ArrayList<Airline>();
        filter.addFilter("name", nameFilterField.getText());
        filter.addFilter("alias", aliasFilterField.getText());
        filter.addFilter("country", countryFilterField.getText());
        data = filter.applyFilters();
        updateTable(data);
    }
}
