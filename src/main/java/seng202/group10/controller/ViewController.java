package seng202.group10.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.model.Airline;
import seng202.group10.model.IncompatibleFileException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ViewController {

    @FXML
    private MenuButton dropdownView;
    @FXML
    private TableView<Airline> airlineTable;
    @FXML
    private MenuItem importAirlinesMenuItem;

    private List<Airline> data;
    private ControllerFacade controllerFacade;


    /**
     * Sets data for airline table in GUI
     */
    public void setAirlineTable() {
        AirlineController airlineController = controllerFacade.getAirlineController();
        data = airlineController.getAirlines();
        airlineTable.setItems(FXCollections.observableList(data));
    }

    /**
     * Shows import airlines menu screen
     */
    public void showImportAirlines() {

        // Create a new file chooser stage
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        // Check if a file was chosen
        if (file != null) {
            String filepath = file.getPath();
            System.out.println(filepath);

            // Import file
            AirlineController controller = new AirlineController();
            try {
                controller.importAirlines(filepath);
            } catch (IncompatibleFileException | IOException e) {
                e.printStackTrace();
            }

            // Update table
            setAirlineTable();

        }
    }

    public void setViewTable() throws IOException {
        System.out.println("Table");
        Stage stage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("table.fxml"));
        stage = (Stage) dropdownView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setViewMap() throws IOException {
        System.out.println("Map");
        Stage stage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage) dropdownView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
