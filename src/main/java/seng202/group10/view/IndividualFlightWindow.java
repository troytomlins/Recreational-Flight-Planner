package seng202.group10.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.group10.model.Flight;
import seng202.group10.model.FlightPoint;

import java.util.ArrayList;

/**
 * Class for Individual Flight Window.
 */
public class IndividualFlightWindow {

    @FXML public Text flightText;
    @FXML public Text aircraftText;
    @FXML public Text distText;
    @FXML public TableView<FlightPoint> legTable;
    @FXML public TableColumn<FlightPoint, String> idCol;
    @FXML public TableColumn<FlightPoint, String> typeCol;
    @FXML public TableColumn<FlightPoint, String> latCol;
    @FXML public TableColumn<FlightPoint, String> lngCol;
    @FXML public TableColumn<FlightPoint, String> altCol;

    private ViewController mainController;
    private Stage stage;
    Flight flight;

    /**
     * Injects the main view controller and stage into object.
     * @param controller View Controller to inject into object
     * @param stage Stage object to inject into stage
     */
    public void initialize(ViewController controller, Stage stage, Flight flight) {
        // Inject variables
        this.mainController = controller;
        this.stage = stage;

        // Set table and text fields
        updateTable(flight.getFlightPoints());
        aircraftText.setText(flight.getAircraftName());
        distText.setText(String.format("%.2f km", flight.getTotalDistance()));
        this.flight = flight;
    }

    /**
     * Populates the flight point table with values in data arraylist.
     * @param data Arraylist of FlightPoint's to populate the table with
     */
    public void updateTable(ArrayList<FlightPoint> data) {
        legTable.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        latCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        lngCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        altCol.setCellValueFactory(new PropertyValueFactory<>("altitude"));
        legTable.setItems(FXCollections.observableList(data));
    }

    /**
     * Closes the window
     */
    public void closeWindow() {
        stage.close();
    }

    /**
     * View the currently selected flight on the map
     */
    public void viewOnMap() {
        SingleSelectionModel<Tab> selectionModel = mainController.mainTabPane.getSelectionModel();
        selectionModel.select(0);
        // Add markers
        mainController.clearMarkers();
        for (FlightPoint point : flight.getFlightPoints()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String code = String.format("newMarker(%f, %f)", point.latitude, point.longitude);
                    mainController.webEngine.executeScript(code);
                }
            });
        }
    }
}
