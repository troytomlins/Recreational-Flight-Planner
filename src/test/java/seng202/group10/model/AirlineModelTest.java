package seng202.group10.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AirlineModelTest {

    private AirlineModel airlineModel;
    private Airline airline;
    private ArrayList<Airline> compareAirline;

    @BeforeEach
    public void init() {
        airlineModel = new AirlineModel();
        airline = new Airline("Air New Zealand", "N/A","NZ","ANZ","NEW ZEALAND","New Zealand");
        compareAirline = new ArrayList<>();
        compareAirline.add(airline);
    }

    @AfterAll
    public static void tearDown() {
        RWStream stream = new RWStream("a");
        stream.closeDb();
    }

    @Test
    public void addAirlineTest() {
        AirlineModel model = new AirlineModel();
        System.out.println(model.getAirlines());
        model.addAirline(airline);
        model.save();
        System.out.println(model.getAirlines());
        assertEquals(1, model.getAirlines().size());
    }

    @Test
    public void addDuplicateAirlineTest() {
        airlineModel.addAirline(airline);
        airlineModel.addAirline(airline); // Try and add the same airline again.
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
