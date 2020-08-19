package seng202.group10.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;

import java.io.IOException;


public class GUIApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        String css = getClass().getResource("mapBackground.css").toExternalForm();
        root.getStylesheets().add(css);
        primaryStage.setTitle("Recreational Flight Planner");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        ControllerFacade controllerFacade = new ControllerFacade();
        launch(args);

    }
}
