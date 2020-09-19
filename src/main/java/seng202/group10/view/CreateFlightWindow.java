package seng202.group10.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seng202.group10.controller.AircraftController;
import seng202.group10.model.Flight;

import java.io.IOException;


/**
 * Controller for the create aircraft window
 * @author Johnny Howe
 */
public class CreateFlightWindow {

    @FXML public Text errorField;

    public FlightTabController controller;
    public Stage stage;

    public void createStopWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createFlightStop.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Create New Stop");
        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(
//                (stage).getScene().getWindow() );
        CreateStopWindow controller = loader.getController();
        controller.injectStage(stage, this);
        stage.show();
    }


    /**
     * Show error message on window
     * @param message Message to show on the window
     */
    public void showErrorMessage(String message) {
        errorField.setText(message);
        errorField.setVisible(true);
    }

    /**
     * Injects the stage into controller
     * @param stage Stage of the window
     */
    public void injectStage(Stage stage, FlightTabController controller) {
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
