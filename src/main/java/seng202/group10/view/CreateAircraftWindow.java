package seng202.group10.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.group10.controller.AircraftController;
import seng202.group10.model.Aircraft;


/**
 * Controller for the create aircraft window
 * @author Tom Rizzi
 */
public class CreateAircraftWindow {

    @FXML public Button cancelButton;
    @FXML public Button createButton;
    @FXML public TextField nameField;
    @FXML public TextField icaoField;
    @FXML public TextField iataField;
    @FXML public TextField rangeField;
    @FXML public Text errorField;

    public AircraftTabController controller;
    public Stage stage;

    /**
     * Attempts to create aircraft from the information in the text fields
     * Validates entry fields, then add to model and update table
     */
    public void createAircraft() {
        // Get Strings
        String name = nameField.getText();
        String icao = icaoField.getText();
        String iata = iataField.getText();

        // Validate fields
        if (name.equals("")) {
            // Name empty
            showErrorMessage("The 'name' field can't be empty!");
            return;
        } else if (icao.equals("")) {
            // Icao empty
            showErrorMessage("The 'icao' field can't be empty!");
            return;
        } else if (iata.equals("")) {
            // Iata empty
            showErrorMessage("The 'iata' field can't be empty!");
            return;
        } else if (rangeField.getText().equals("")) {
            // Range empty
            showErrorMessage("The 'range' field can't be empty!");
            return;
        } else {

            double range;
            try {
                range = Double.parseDouble(rangeField.getText());
            } catch (NumberFormatException e) {
                // Range not numeric
                showErrorMessage("The 'range' field must be numeric!");
                return;
            }

            // All fields valid, add aircraft to model and update table
            AircraftController aircraftController = controller.mainController.controllerFacade.getAircraftController();
            aircraftController.addAircraft(iata, name, icao, range);
            stage.close();
            controller.updateTable(aircraftController.getAircraft());
        }
    }

    /**
     * Show error message on window
     * @param message - Message to show on the window
     */
    public void showErrorMessage(String message) {
        errorField.setText(message);
        errorField.setVisible(true);
    }

    /**
     * Injects the stage into controller
     * @param stage - Stage of the window
     * @param controller - the controller for AircraftTabs
     */
    public void injectStage(Stage stage, AircraftTabController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    /**
     * Closes the create aircraft modal
     * @param actionEvent - cancel button is pressed
     */
    public void cancel(ActionEvent actionEvent) {
        stage.close();

    }
}
