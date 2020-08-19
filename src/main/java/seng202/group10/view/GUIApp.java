package seng202.group10.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.controller.ViewController;

import java.io.IOException;


public class GUIApp extends Application {

    private Stage stage;
    private ControllerFacade controllerFacade;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.controllerFacade = new ControllerFacade();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        ViewController controller = fxmlLoader.getController();
        controller.setControllerFacade(this.controllerFacade);

        String css = getClass().getResource("mapBackground.css").toExternalForm();
        root.getStylesheets().add(css);
        primaryStage.setTitle("Recreational Flight Planner");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
