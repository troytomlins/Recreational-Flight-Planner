package seng202.group10.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import seng202.group10.model.*;

import java.io.File;

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
        AirlineController controllerA = new AirlineController();
        AirlineController controllerB = new AirlineController();;
        try {

            controllerA.importAirlines("src/test/resources/seng202.group10/model/airportsGood.dat");
            controllerA.writeAirports(outFile);


            controllerB.importAirlines(outFile);

            assertEquals(controllerA.getAirlines().size(), controllerB.getAirlines().size());
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
