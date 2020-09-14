package seng202.group10.view;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import seng202.group10.controller.ControllerFacade;

import java.net.URL;

public class GUIApp extends Application {

    private JavaConnector javaConnector = new JavaConnector();
    private ControllerFacade controllerFacade;
    public ViewController viewController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();

        // Set controller
        viewController = fxmlLoader.getController();
        controllerFacade = new ControllerFacade();
        viewController.setControllerFacade(controllerFacade);

        // Start Scene
        Scene scene = new Scene(root, 1000, 650);

        // Add Google Maps
        WebView webView = (WebView) scene.lookup("#mapWebView");
        URL url = this.getClass().getResource("GoogleMaps/index.html");
        final WebEngine webEngine = webView.getEngine();

        // Set up java/javascript bridge
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
            }
        });

        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recreational Flight Planner");
        primaryStage.show();

        // now load the page
        webEngine.load(url.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Object to bridge the gap between java and javascript
     * Used to give the java app the markers set in the google maps API
     */
    public class JavaConnector {
        public void newLatLng(String id, float lat, float lng) {
            viewController.newMarker(id, lat, lng);
        }
    }
}
