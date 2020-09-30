package seng202.group10.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import seng202.group10.model.*;

import java.io.File;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirlineControllerTest {

    @Test
    public void importAirlinesTest() {
        // TODO Write Test

        return;
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

    @AfterAll
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
    }
}
