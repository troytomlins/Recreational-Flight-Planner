package seng202.group10.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import seng202.group10.controller.ControllerFacade;
import seng202.group10.model.Airport;
import seng202.group10.view.ViewController;
import seng202.group10.model.DatabaseConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX GUI app
 * Must be run from another class
 *
 * Handles the creation of the stage, scene and bits inside (loading fxml)
 * @author Tom Rizzi
 * @author Johnny Howe
 */
public class GUIApp extends Application {

    private JavaConnector javaConnector = new JavaConnector();  // Object for js to talk to java
    public ViewController viewController;

    /**
     * Override Application.start
     * Loads up the scene from the main.fxml file, sets the controller (according to setController),
     *  adds google maps (according to addGoogleMaps).
     * @param primaryStage - stage to set the scene on
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();

        setController(fxmlLoader, primaryStage);
        Scene scene = new Scene(root, 1000, 650);
        addGoogleMaps(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Recreational Flight Planner");
        primaryStage.show();

    }

    /**
     * Load up google maps into the scene into the mapsWebView node
     * Looks for the resource at GoolgeMaps/index.html to insert
     * @param scene - scene to add maps
     */
    public void addGoogleMaps(Scene scene) {
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
        javaConnector.setEngine(webEngine);
        webEngine.load(url.toString());
    }

    @Override
    public void stop() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.disconnect();
    }
    /**
     * Sets the fxml controller facade to a new controllerFacade
     * sets viewController and its stage to primary stage
     * @param fxmlLoader - main.fxml loader
     * @param primaryStage - app stage
     */
    public void setController(FXMLLoader fxmlLoader, Stage primaryStage) {
        viewController = fxmlLoader.getController();
        viewController.stage = primaryStage;
    }

    /**
     * Object to bridge the gap between java and javascript
     * Used to give the java app the markers set in the google maps API
     */
    public class JavaConnector {

        /**
         * Add a new marker to the viewController
         * @param id - string identifier of the marker
         * @param lat - latitude
         * @param lng - longitude
         */
        public void newLatLng(String id, float lat, float lng) {
            viewController.newMarker(id, lat, lng);
        }

        ArrayList<String> labels;
        ArrayList<Float> lats;
        ArrayList<Float> lngs;

        public void clearMarkers() {
            labels = new ArrayList<>();
            lats = new ArrayList<>();
            lngs = new ArrayList<>();
        }

        public void addMarker(String label, float lat, float lng) {
            labels.add(label);
            lats.add(lat);
            lngs.add(lng);
        }

        public void confirmMarkers() {
            // Example on how things go
//            for (int i = 0; i < labels.size(); i ++) {
//                String id = labels.get(i);
//                float lat = lats.get(i);
//                float lng = lngs.get(i);
//                viewController.newMarker(id, lat, lng);
//            }
            viewController.setMarkers(labels, lats, lngs);
        }

        /**
         * Throw some text in the java console
         * @param text text to print
         */
        public void println(String text) {
            System.out.println(text);
        }
        WebEngine webEngine;

        public void setEngine(WebEngine engine) {
            webEngine = engine;
        }

        public void setAirports() {
            System.out.println("Here");
            ArrayList<Airport> airports = new ArrayList<Airport>();
            for (Airport airport: viewController.controllerFacade.getAirportController().getAirports()) {
                //TODO: Only send airports that are on the screen
                airports.add(airport);
            }

            for (int i = 0; i < Math.min(100, airports.size()); i++) {
                Airport airport = airports.get(i);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        webEngine.executeScript(String.format("addAirport('%s', %f, %f)", airport.getName(), airport.getLatitude(), airport.getLongitude()));
                    }
                });
            }
        }
    }

    /**
     * Run the app
     * @param args - standard command line args, are ignored
     */
    public static void main(String[] args) {
        launch(args);
    }
}
