package seng202.group10.model;

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
        File file = new File("database.db");
        file.delete();
        airlineModel = new AirlineModel();
        airline = new Airline("Air New Zealand", "N/A","NZ","ANZ","NEW ZEALAND","New Zealand");
        compareAirline = new ArrayList<>();
        compareAirline.add(airline);
    }

    @Test
    public void addAirlineTest() {
        airlineModel.addAirline(airline);
        airlineModel.save();
        assertEquals(1, airlineModel.getAirlines().size());
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
