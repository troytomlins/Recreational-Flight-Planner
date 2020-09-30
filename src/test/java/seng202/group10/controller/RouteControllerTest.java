package seng202.group10.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.group10.model.DatabaseConnection;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteControllerTest {

    public RouteController controller;

    @BeforeAll
    static void setup() {
        DatabaseConnection.getInstance().disconnect();
    }

    @BeforeEach
    public void init() {
        controller = new RouteController();
    }

    @AfterEach
    public void teardown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void writeAirportsTest() throws FileFormatException, IncompatibleFileException {
        String outFile = "test.txt";
        RouteController controllerB = new RouteController();;
        try {

            controller.importRoutes("src/test/resources/seng202.group10/model/routesGood.dat");
            controller.writeRoutes(outFile);


            controllerB.importRoutes(outFile);

            assertEquals(controller.getRoutes().size(), controllerB.getRoutes().size());
        } finally {
            File file = new File(outFile);
            file.delete();
        }
    }
}
