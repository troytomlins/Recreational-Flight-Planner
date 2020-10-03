package seng202.group10.controller;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import seng202.group10.model.*;

import java.io.File;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

public class AirlineControllerTest {

    @BeforeEach
    public void setup() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
    }

    @Test
    public void importAirlinesTest() throws FileFormatException, IncompatibleFileException {
        String filename = "src/test/resources/seng202/group10/controller/airlinesGood.dat";
        AirlineController controller = new AirlineController();
        controller.importAirlines(filename);
        assertEquals(controller.getAirlines().size(), 3);
    }

    @Test
    public void writeAirportsTest() throws FileFormatException, IncompatibleFileException {
        String outFile = "test.txt";
        AirlineController controller = new AirlineController();
        try {
            // Setup
            controller.importAirlines("src/test/resources/seng202.group10/model/airportsGood.dat");
            controller.writeAirlines(outFile);
            ArrayList<Airline> beforeContent = controller.getAirlines();

            // Load file and check correct
            DatabaseConnection.getInstance().disconnect();
            controller = new AirlineController();

            assertEquals(controller.getAirlines().size(), beforeContent.size());
        } finally {

            File file = new File(outFile);
            file.delete();
        }
    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
    }
}
