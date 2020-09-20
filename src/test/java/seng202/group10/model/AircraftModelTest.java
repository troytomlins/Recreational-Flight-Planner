package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AircraftModelTest {

    private AircraftModel aircraftModel;
    private Aircraft aircraft;
    private ArrayList<Aircraft> compareCraft;

    @BeforeEach
    public void init() {
        aircraftModel = new AircraftModel(false);
        aircraft = new Aircraft("test", "testCraft", "test", 1000);
        compareCraft = new ArrayList<>();
        compareCraft.add(aircraft);
    }

    @Test
    public void addAircraftTest() {
        aircraftModel.addAircraft(aircraft);
        System.out.println(aircraftModel.getAircraftList());
        System.out.println(compareCraft);
        assertEquals(compareCraft, aircraftModel.getAircraftList());
    }

    @Test
    public void addDuplicateAircraftTest() {
        aircraftModel.addAircraft(aircraft);
        aircraftModel.addAircraft(aircraft); // Try and add the same aircraft again.
        assertEquals(compareCraft, aircraftModel.getAircraftList()); // The duplicate should not be added.
    }

    @Test
    public void deleteAircraftTest() {
        int count = 0;
        ArrayList<Aircraft> testArray;
        aircraftModel.addAircraft(aircraft);
        aircraftModel.deleteAircraft(aircraft);
        testArray = aircraftModel.getAircraftList();
        for (Aircraft craft : testArray) {
            count += 1;
        }
        assertEquals(0, count);
    }
}
