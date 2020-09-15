package seng202.group10.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.group10.controller.AircraftController;
import seng202.group10.model.Aircraft;

public class CreateAircraftWindow {


    @FXML public Button cancelButton;
    @FXML public Button createButton;
    @FXML public TextField nameField;
    @FXML public TextField icaoField;
    @FXML public TextField iataField;
    @FXML public TextField rangeField;

    public AircraftTabController controller;
    public Stage stage;




    /**
     * Method validates entry fields, then
     * @param actionEvent
     */
    public void createAircraft(ActionEvent actionEvent) {
        String name = nameField.getText();
        String icao = icaoField.getText();
        String iata = iataField.getText();

        if (name.equals("") ||
            icao.equals("") ||
            iata.equals("") ||
            rangeField.getText().equals("") ) {
            // TODO Implement on incomplete variables
            return;
        } else {
            double range = Double.parseDouble(rangeField.getText());
            AircraftController aircraftController = controller.mainController.controllerFacade.getAircraftController();
            aircraftController.addAircraft(iata, name, icao, range);
            stage.close();
            controller.updateTable();
        }

    }

    /**
     * Injects the stage into controller
     * @param stage Stage of the window
     */
    public void injectStage(Stage stage, AircraftTabController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Closes the create aircraft modal
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        stage.close();

    }
}
