package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void addAirlineTest() {
        airlineModel.addAirline(airline);
        assertEquals(compareAirline, airlineModel.getAirlines());
    }

    @Test
    public void addDuplicateAirlineTest() {
        airlineModel.addAirline(airline);
        airlineModel.addAirline(airline); // Try and add the same airline again.
        assertEquals(compareAirline, airlineModel.getAirlines()); // The duplicate should not be added.
    }

    @Test
    public void deleteAirlineTest() {
        compareAirline = new ArrayList<>();
        airlineModel.addAirline(airline);
        airlineModel.deleteAirline(airline);
        assertEquals(compareAirline, airlineModel.getAirlines());
    }

}
