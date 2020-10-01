package seng202.group10.controller;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import seng202.group10.model.DatabaseConnection;
import seng202.group10.model.FileFormatException;
import seng202.group10.model.IncompatibleFileException;
import seng202.group10.model.Route;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteControllerTest {

    public RouteController controller;

    @BeforeClass
    public static void setup() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Before
    public void init() {
        controller = new RouteController();
    }

    @After
    public void teardown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void writeRoutesTest() throws FileFormatException, IncompatibleFileException {
        String outFile = "testWriteRoutes.txt";
        try {

            controller.importRoutes("src/test/resources/seng202.group10/model/routesGood.dat");
            controller.writeRoutes(outFile);
            ArrayList<Route> beforeRoutes = controller.getRoutes();

            DatabaseConnection.getInstance().disconnect();
            new File("database.db").delete();
            controller = new RouteController();
            controller.importRoutes(outFile);

            assertEquals(beforeRoutes.size(), controller.getRoutes().size());
        } finally {
            File file = new File(outFile);
            file.delete();
        }
    }
}
