package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AircraftModelTest {

    private AircraftModel aircraftModel;
    private Aircraft aircraft;
    private ArrayList<Aircraft> compareCraft;

    @BeforeEach
    public void init() {
        aircraftModel = new AircraftModel();
        aircraft = new Aircraft("test", "testCraft", "test", 1000);
        compareCraft = new ArrayList<>();
        compareCraft.add(aircraft);
    }

    @Test
    public void addAircraftTest() {
        aircraftModel.addAircraft(aircraft);
        assertEquals(compareCraft, aircraftModel.getAircraftList());
    }

    @Test
    public void addDuplicateAircraftTest() {
        aircraftModel.addAircraft(aircraft);
        aircraftModel.addAircraft(aircraft); // Try and add the same aircraft again.
        assertEquals(compareCraft, aircraftModel.getAircraftList()); // The duplicate should not be added.
    }
}
