package seng202.group10.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class AircraftModelTest {

    private AircraftModel aircraftModel;
    private Aircraft aircraft;
    private ArrayList<Aircraft> compareCraft;

    @Before
    public void init() {
        aircraftModel = new AircraftModel();
        aircraft = new Aircraft("test", "testCraft", "test", 1000);
        compareCraft = new ArrayList<>();
        compareCraft.add(aircraft);
    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void addAircraftTest() {
        Integer beforeSize = aircraftModel.getAircraftList().size();
        aircraftModel.addAircraft(aircraft);
        Integer afterSize = aircraftModel.getAircraftList().size();
        assertEquals((beforeSize + 1), (int) afterSize);
    }

    @Test
    public void addDuplicateAircraftTest() {
        System.out.println(aircraftModel.getIndexOf(aircraft));
        while (aircraftModel.getIndexOf(aircraft) != -1) {
            aircraftModel.deleteAircraft(aircraft);
        }
        int beforeSize = aircraftModel.getAircraftList().size();
        aircraftModel.addAircraft(aircraft);
        aircraftModel.addAircraft(aircraft); // Try and add the same aircraft again.
        int afterSize = aircraftModel.getAircraftList().size();
        assertEquals((beforeSize + 1), (int) afterSize); // The duplicate should not be added.
    }

    @Test
    public void deleteAircraftTest() {
        aircraftModel.deleteAircraft(aircraft);
        int beforeCount = aircraftModel.getAircraftList().size();
        aircraftModel.addAircraft(aircraft);
        aircraftModel.deleteAircraft(aircraft);
        int afterCount = aircraftModel.getAircraftList().size();
        assertEquals(beforeCount, afterCount);
    }
}
