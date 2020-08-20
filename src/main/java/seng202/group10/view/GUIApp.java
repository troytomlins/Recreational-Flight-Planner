package seng202.group10.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;

import seng202.group10.controller.ControllerFacade;

import java.io.IOException;


public class GUIApp extends Application {

//    @FXML
//    WebView mapWebView;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Recreational Flight Planner");
        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);
        primaryStage.show();

        WebView mapView = (WebView) scene.lookup("#mapWebView");
        WebEngine mapEngine = mapView.getEngine();
        mapEngine.load("https://www.google.co.nz/maps");
    }

    public static void main(String[] args) {
        ControllerFacade controllerFacade = new ControllerFacade();
        launch(args);

    }
}
