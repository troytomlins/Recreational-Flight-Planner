package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.controller.AirportController;
import seng202.group10.controller.filters.AirportFilters;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;

import java.util.ArrayList;

/**
 * Controller for the airport tab of the GUI
 * @author Tom Rizzi
 */
public class AirportTabController {

    // FXML things
    @FXML public TableView<Airport> airportTable;
    @FXML private TableColumn<Airline, String> cityCol;
    @FXML private TableColumn<Airline, String> latCol;
    @FXML private TableColumn<Airline, String> lngCol;
    @FXML private TableColumn<Airline, String> altCol;
    @FXML private TableColumn<Airline, String> tzCol;
    @FXML private TableColumn<Airline, String> distCol;
    @FXML private TableColumn<Airline, String> tzdbCol;
    @FXML private TableColumn<Airline, String> nameCol;
    @FXML private TableColumn<Airline, String> iataCol;
    @FXML private TableColumn<Airline, String> icaoCol;
    @FXML private TableColumn<Airline, String> countryCol;
    @FXML private ViewController mainController;
    @FXML private TextField nameFilterField;
    @FXML private TextField cityFilterField;
    @FXML private TextField countryFilterField;

    public void updateTable(ArrayList<Airport> data) {
        airportTable.setEditable(true);
        nameCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("name"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("city"));
        latCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("latitude"));
        lngCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("longitude"));
        altCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("altitude"));
        tzCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("timezone"));
        distCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("dstType"));
        tzdbCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("tzDatabase"));
        iataCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("iata"));
        icaoCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("icao"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("country"));
        airportTable.setItems(FXCollections.observableList(data));
    }

    /**
     * Sets this.mainController
     * @param controller to set
     */
    public void injectMainController(ViewController controller) {

        this.mainController = controller;
    }

    /**
     * Opens file explorer for user to select a file
     * once a file is selected, import it to the controller
     * If file not compatible (not csv), a message is printed to the console.
     * Once imported, update the table
     */
    public void importAirports() {
        // Pick file
        String filepath = mainController.showFileExplorer();

        // Check if a file was chosen
        if (filepath != null) {

            // Import file
            AirportController controller = mainController.controllerFacade.getAirportController();
            try {
                controller.importAirports(filepath);

                // Update table
                ArrayList<Airport> data = controller.getAirports();
                updateTable(data);

            } catch (IncompatibleFileException e) {
                mainController.showIncompatibleFileError(e);

            } catch (FileFormatException e) {
                Boolean tryAgain = mainController.showFileFormatError(e);
                if (tryAgain) {

                    // Try import airports again, this time ignoring error lines

                    try {
                        controller.importAirports(filepath, e.getLines());
                    } catch (IncompatibleFileException | FileFormatException err) {
                        mainController.showErrorWindow(err.getMessage());
                    }

                    // Update table
                    ArrayList<Airport> data = controller.getAirports();
                    updateTable(data);

                    // Show success message
                    mainController.showInfoWindow("Successfully imported airport data");
                }
            }


        }
    }

    /**
     * Apply the selected/typed filters to the data, update the shown table
     */
    public void applyAirportFilters() {
        AirportFilters filter = new AirportFilters();
        AirportController airports = new AirportController();
        ArrayList<Airport> data = new ArrayList<Airport>();
        filter.addFilter("name", nameFilterField.getText());
        filter.addFilter("city", cityFilterField.getText());
        filter.addFilter("country", countryFilterField.getText());
        data = filter.applyFilters();
        updateTable(data);
    }
}
