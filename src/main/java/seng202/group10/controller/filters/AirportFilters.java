package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.AirportRW;

import java.util.ArrayList;

/**
 * @Author Niko Tainui
 */

public class AirportFilters {
    /**
     * Get a new Arraylist of all the airports which contains a specific string in name.
     * @param contains (String): substring to filter for
     * @return ArrayList<Airline>
     */

    public ArrayList<Airport> filterByName(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        for (Airport airport : airportData) {
            if (airport.getName().contains(contains)) {
                filteredAirports.add(airport);
            }
        }
        return filteredAirports;
    }

    public ArrayList<Airport> filterByCity(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        for (Airport airport : airportData) {
            if (airport.getCity().contains(contains)) {
                filteredAirports.add(airport);
            }
        }
        return filteredAirports;
    }

    public ArrayList<Airport> filterByCountry(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        for (Airport airport : airportData) {
            if (airport.getCountry().contains(contains)) {
                filteredAirports.add(airport);
            }
        }
        return filteredAirports;
    }
}