package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AirportModelTest {

    private AirportModel airportModel;
    private Airport airport;
    private ArrayList<Airport> compareAirport;

    @BeforeEach
    public void init() {
        airportModel = new AirportModel();
        File file = new File("database.db");
        file.delete();
        airport = new Airport("Christchurch Intl","Christchurch","New Zealand","CHCa","NZCHa",-43.489358,172.532225,123,12,"Z","Pacific/Auckland");
        compareAirport = new ArrayList<>();
        compareAirport.add(airport);
    }

    @Test
    public void addAirportTest() {
        airportModel.deleteAirport(airport);
        int beforeCount = airportModel.getAirports().size();
        airportModel.addAirport(airport);
        airportModel.save();
        int afterCount = airportModel.getAirports().size();
        assertEquals(beforeCount + 1, afterCount);
    }
}
