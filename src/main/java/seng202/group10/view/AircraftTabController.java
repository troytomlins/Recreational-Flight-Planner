package seng202.group10.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.controller.AircraftController;
import seng202.group10.model.Aircraft;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the airlines tab
 * @author Tom Rizzi
 */
public class AircraftTabController {

    // FXML things
    @FXML public TableColumn nameCol;
    @FXML public TableColumn iataCol;
    @FXML public TableColumn icaoCol;
    @FXML public TableColumn rangeCol;
    @FXML public TableView aircraftTable;
    public ViewController mainController;

    /**
     * Injects main view controller into this controller
     * @param controller - View controller to inject
     */
    public void injectMainController(ViewController controller) {
        this.mainController = controller;
    }

    public void importAircraft(ActionEvent actionEvent) {
        // TODO Implement
    }

    /**
     * Create a new "create aircraft" window/stage
     * @param event - when user clicks on create aircraft
     * @throws IOException - when loader does not load correctly this exception is thrown
     */
    public void showCreateAircraftModel(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createAircraft.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Create New Aircraft");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                (mainController.stage).getScene().getWindow() );
        CreateAircraftWindow controller = loader.getController();
        controller.injectStage(stage, this);
        stage.show();
    }

    /**
     * Updates aircraft table with data currently in the aircraft model
     * @param data - updates table with aircraft data
     */
    public void updateTable(ArrayList<Aircraft> data) {
        aircraftTable.setEditable(true);

        nameCol.setCellValueFactory(new PropertyValueFactory<Aircraft, String>("name"));
        iataCol.setCellValueFactory(new PropertyValueFactory<Aircraft, String>("iata"));
        icaoCol.setCellValueFactory(new PropertyValueFactory<Aircraft, String>("icao"));
        rangeCol.setCellValueFactory(new PropertyValueFactory<Aircraft, String>("range"));

        aircraftTable.setItems(FXCollections.observableList(data));
    }
}
