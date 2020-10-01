package seng202.group10.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FlightModelTest {
    private FlightModel flightModel;
    private Flight flight;
    private ArrayList<Flight> compareFlight;

    @Before
    public void init() {
        flightModel = new FlightModel();
        flight = new Flight();
        compareFlight = new ArrayList<>();
        compareFlight.add(flight);

    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void addFlightTest(){
        flightModel.addFlight(flight);
        assertEquals(compareFlight, flightModel.getFlights());
    }

    @Test
    public void addDuplicateFlightTest() {
        flightModel.addFlight(flight);
        flightModel.addFlight(flight); // Try and add the same flight again.
        assertEquals(compareFlight, flightModel.getFlights()); // The duplicate should not be added.
    }
}
