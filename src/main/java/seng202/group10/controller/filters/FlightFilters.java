package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.AirportRW;
import seng202.group10.model.DatabaseConnection;

import java.util.ArrayList;

//TODO write the filters depending on GUI
/**
 * @Author Niko Tainui
 * @Author Johnny Howe
 */

public class FlightFilters {
    /**
     * Get a new Arraylist of all the points which contains a specific string in name.
     * @param contains (String): substring to filter for
     * @return ArrayList<Points>
     */

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public ArrayList<Airport> filterByName(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        if (contains == "") {
            filteredAirports = airportData;
        } else {
            for (Airport airport : airportData) {
                if (airport.getName().contains(contains)) {
                    filteredAirports.add(airport);
                }
            }
        }
        return filteredAirports;
    }

    public ArrayList<Airport> filterByCity(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        if (contains == "") {
            filteredAirports = airportData;
        } else {
            for (Airport airport : airportData) {
                if (airport.getCity().contains(contains)) {
                    filteredAirports.add(airport);
                }
            }
        }
        return filteredAirports;
    }

    public ArrayList<Airport> filterByCountry(ArrayList<Airport> airportData, String contains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        if (contains == "") {
            filteredAirports = airportData;
        }
        for (Airport airport : airportData) {
            if (airport.getCountry().contains(contains)) {
                filteredAirports.add(airport);
            }
        }
        return filteredAirports;
    }

    //TODO write error checking for filters making sure data is loaded.

    public ArrayList<Airport> filterByAll(ArrayList<Airport> airportData, String name, String city, String country) {
        ArrayList<Airport> airportsByName = new ArrayList<>();
        ArrayList<Airport> airportsByNameCity = new ArrayList<>();
        ArrayList<Airport> airportsByNameCityCountry = new ArrayList<>();
        airportsByName = filterByName(airportData, name);
        airportsByNameCity = filterByCity(airportsByName, city);
        airportsByNameCityCountry = filterByCountry(airportsByNameCity, country);
        return airportsByNameCityCountry;
    }
}