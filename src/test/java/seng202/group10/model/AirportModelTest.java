package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AirportModelTest {

    private AirportModel airportModel;
    private Airport airport;
    private ArrayList<Airport> compareAirport;

    @BeforeEach
    public void init() {
        File file = new File("database.db");
        file.delete();
        airportModel = new AirportModel();
        airport = new Airport("Christchurch Intl","Christchurch","New Zealand","CHCa","NZCHa",-43.489358,172.532225,123,12,"Z","Pacific/Auckland");
        compareAirport = new ArrayList<>();
        compareAirport.add(airport);
    }

    @Test
    public void addAirportTest() {
        int beforeCount = airportModel.getAirports().size();
        airportModel.addAirport(airport);
        int afterCount = airportModel.getAirports().size();
        assertEquals(beforeCount + 1, afterCount);
    }
}
