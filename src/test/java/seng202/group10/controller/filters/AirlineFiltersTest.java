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
        validData.add(new Airline("aname32542928350", "aliasf", "````", "icao", "callsign", "country2"));

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

    @Test
    public void filterByAliasNoDataNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByAlias(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByAliasNoDataTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByAlias(data, "aaaaa");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByAliasNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        data.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        ArrayList<Airline> sortedData = airlineFilters.filterByAlias(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByAliasTest() {
        AirlineFilters airlineFilters = new AirlineFilters();

        // Build up valid data array
        ArrayList<Airline> validData = new ArrayList<>();
        validData.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        validData.add(new Airline("name32542928350", "aaliasf", "````", "icao", "callsign", "country2"));

        // Build up data array
        ArrayList<Airline> data = new ArrayList<>();
        data.add(validData.get(0));
        data.add(new Airline("nam2", "alis", "iata", "icao", "callsign", "country"));
        data.add(validData.get(1));
        data.add(new Airline("nme3", "no", "iata", "icao", "callsign", "country2"));

        // Is the filtered array the same as the valid data?
        ArrayList<Airline> sortedData = airlineFilters.filterByAlias(data, "alias");
        assertArrayEquals(validData.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByCountryNoDataNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByCountry(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByCountryNoDataTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        ArrayList<Airline> sortedData = airlineFilters.filterByCountry(data, "aaaaa");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByCountryNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> data = new ArrayList<>();
        data.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        ArrayList<Airline> sortedData = airlineFilters.filterByCountry(data, "");
        assertArrayEquals(data.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByCountryTest() {
        AirlineFilters airlineFilters = new AirlineFilters();

        // Build up valid data array
        ArrayList<Airline> validData = new ArrayList<>();
        validData.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        validData.add(new Airline("name32542928350", "aliasf", "````", "icao", "callsign", "aasdacountry2"));

        // Build up data array
        ArrayList<Airline> data = new ArrayList<>();
        data.add(validData.get(0));
        data.add(new Airline("nam2", "alis", "iata", "icao", "callsign", "county"));
        data.add(validData.get(1));
        data.add(new Airline("nme3", "no", "iata", "icao", "callsign", "yues"));

        // Is the filtered array the same as the valid data?
        ArrayList<Airline> sortedData = airlineFilters.filterByCountry(data, "country");
        assertArrayEquals(validData.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByAllNoDataNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> sortedData = airlineFilters.filterByAll(new ArrayList<Airline>(), "", "", "");
        assertArrayEquals(sortedData.toArray(), (new ArrayList<Airline>()).toArray());
    }

    @Test
    public void filterByAllNoDataTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        ArrayList<Airline> sortedData = airlineFilters.filterByAll(new ArrayList<Airline>(), "NaMe", "aliAs", "couNTRY");
        assertArrayEquals(sortedData.toArray(), (new ArrayList<Airline>()).toArray());
    }

    @Test
    public void filterByAllNoContainsTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        // Build up valid data array
        ArrayList<Airline> validData = new ArrayList<>();
        validData.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        validData.add(new Airline("name32542928350", "aliasf", "````", "icao", "callsign", "aasdacountry2"));

        // Build up data array
        ArrayList<Airline> data = new ArrayList<>();
        data.add(validData.get(0));
        data.add(validData.get(1));

        // Is the filtered array the same as the valid data?
        ArrayList<Airline> sortedData = airlineFilters.filterByAll(data, "", "", "");
        assertArrayEquals(validData.toArray(), sortedData.toArray());
    }

    @Test
    public void filterByAllTest() {
        AirlineFilters airlineFilters = new AirlineFilters();
        // Build up valid data array
        ArrayList<Airline> validData = new ArrayList<>();
        validData.add(new Airline("name", "alias", "iata", "icao", "callsign", "country"));
        validData.add(new Airline("name32542928350", "aliasf", "````", "icao", "callsign", "aasdacountry2"));

        // Build up data array
        ArrayList<Airline> data = new ArrayList<>();
        data.add(validData.get(0));
        data.add(new Airline("name", "aias", "iata", "icao", "callsign", "country"));
        data.add(new Airline("nae", "alias", "iata", "icao", "callsign", "country"));
        data.add(new Airline("name", "alias", "iata", "icao", "callsign", "county"));
        data.add(validData.get(1));
        data.add(new Airline("nAS", "AASFF", "iata", "icao", "callsign", "AAAJF"));

        // Is the filtered array the same as the valid data?
        ArrayList<Airline> sortedData = airlineFilters.filterByAll(data, "name", "alias", "country");
        assertArrayEquals(validData.toArray(), sortedData.toArray());
    }
}
