package seng202.group10.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test Class for AirlineModel.
 */
public class AirlineModelTest {

    private AirlineModel airlineModel;
    private Airline airline;
    private ArrayList<Airline> compareAirline;

    @Before
    public void init() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
        airlineModel = new AirlineModel();
        airline = new Airline("Air New Zealand", "N/A","NZ","ANZ","NEW ZEALAND","New Zealand");
        compareAirline = new ArrayList<>();
        compareAirline.add(airline);
    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
    }

    @Test
    public void addAirlineTest() {
        AirlineModel model = new AirlineModel();
        model.addAirline(airline);
        model.save();
        assertEquals(1, model.getAirlines().size());
    }

    @Test
    public void addDuplicateAirlineTest() {
        airlineModel.addAirline(airline);
        airlineModel.addAirline(airline); // Try and add the same airline again.
        airlineModel.save();
        assertEquals(1, airlineModel.getAirlines().size()); // The duplicate should not be added.
    }

//    @Test
//    public void deleteAirlineTest() {
//        compareAirline = new ArrayList<>();
//        airlineModel.addAirline(airline);
//        airlineModel.deleteAirline(airline);
//        airlineModel.save();
//        assertEquals(0, airlineModel.getAirlines().size());
//    }

}
