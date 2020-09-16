package seng202.group10.controller.filters;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

import seng202.group10.model.Airline;

public class AirlineFiltersTest {

    @Test
    public void filterByNameNoDataNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByName(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByNameNoDataTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByName(data, "aaaaa");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByNameNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        data.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        ArrayList<Airline> sortedData = airlineFilters.filterByName(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByNameTest() {
        AirlineFilters airlineFilters = new AirlineFilters();

        // Build up valid data array
        ArrayList<Airline> validData = new ArrayList<>();
        validData.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        validData.add(new Airline("name32542928350", "aliasf", "````", "icao", "callsign", "country2"));

        // Build up data array
        ArrayList<Airline> data = new ArrayList<>();
        data.add(validData.get(0));
        data.add(new Airline("nam2", "alias", "iata", "icao", "callsign", "country"));
        data.add(validData.get(1));
        data.add(new Airline("nme3", "alias", "iata", "icao", "callsign", "country2"));

        // Is the filtered array the same as the valid data?
        ArrayList<Airline> sortedData = airlineFilters.filterByName(data, "name");
        assertArrayEquals(validData.toArray(), sortedData.toArray());
    }
}
