package seng202.group10.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import seng202.group10.controller.AirportController;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Class for AirportModel.
 */
public class AirportModelTest {

    private AirportModel airportModel;
    private Airport airport;
    private ArrayList<Airport> compareAirport;

    @Before
    public void init() {
        airportModel = new AirportModel();
        airport = new Airport("Christchurch Intl","Christchurch","New Zealand","CHCa","NZCHa",-43.489358,172.532225,123,12,"Z","Pacific/Auckland");
        compareAirport = new ArrayList<>();
        compareAirport.add(airport);
    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
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

    @Test
    public void saveToFileTest() {
        String filepath = "testAirports";
        try {
            ArrayList<Airport> beforeArray = airportModel.getAirports();
            airportModel.saveToFile(filepath);
            AirportController newController = new AirportController();
            newController.importAirports(filepath);
            assertEquals(beforeArray.size(), newController.getAirports().size());
        }
        catch (Exception error) {
            error.printStackTrace();
        }
        finally {
            File file = new File(filepath);
            file.delete();
        }

    }
}
