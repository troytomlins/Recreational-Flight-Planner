package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AirportModelTest {

    private AirportModel airportModel;
    private Airport airport;
    private ArrayList<Airport> compareAirport;

    @BeforeEach
    public void init() {
        airportModel = new AirportModel();
        airport = new Airport("Christchurch Intl","Christchurch","New Zealand","CHC","NZCH",-43.489358,172.532225,123,12,"Z","Pacific/Auckland");
        compareAirport = new ArrayList<>();
        compareAirport.add(airport);
    }

    @Test
    public void addAirportTest() {
        airportModel.addAirport(airport);
        assertEquals(compareAirport, airportModel.getAirports());
    }

    @Test
    public void addDuplicateAirportTest() {
        airportModel.addAirport(airport);
        airportModel.addAirport(airport); // Try and add the same airport again.
        assertEquals(compareAirport, airportModel.getAirports()); // The duplicate should not be added.
    }

}
