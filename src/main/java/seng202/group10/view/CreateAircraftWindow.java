package seng202.group10.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateAircraftWindow {


    @FXML public Button cancelButton;
    @FXML public Button createButton;

    public AircraftTabController controller;
    public Stage stage;


    /**
     * Method validates entry fields, then
     * @param actionEvent
     */
    public void createAircraft(ActionEvent actionEvent) {
        // TODO Implement create aircraft
    }

    /**
     * Injects the stage into controller
     * @param stage Stage of the window
     */
    public void injectStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Closes the create aircraft modal
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        stage.close();

    }
}
