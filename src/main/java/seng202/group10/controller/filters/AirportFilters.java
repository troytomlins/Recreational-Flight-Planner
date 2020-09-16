package seng202.group10.controller.filters;

import seng202.group10.model.Airline;
import seng202.group10.model.Airport;
import seng202.group10.model.AirportRW;
import seng202.group10.model.DatabaseConnection;

import java.util.ArrayList;

/**
 * Class to filter the airport data.
 * @Author Jonathon Howe
 * @Author Niko Tainui
 */

public class AirportFilters {

    // TODO doc this
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    /**
     * Get a new Arraylist of all the airlines with contains string in name
     * @param airportData data to filter through
     * @param contains substring to filter for
     * @return the airport data filtered by names containing contains
     */
    public ArrayList<Airport> filterByName(ArrayList<Airport> airportData, String contains) {
        return filterByAll(airportData, contains, "", "");
    }

    /**
     * Get a new Arraylist of all the airlines with contains string in city
     * @param airportData data to filter through
     * @param contains substring to filter for
     * @return the airport data filtered by cities containing contains
     */
    public ArrayList<Airport> filterByCity(ArrayList<Airport> airportData, String contains) {
        return filterByAll(airportData, "", contains, "");
    }

    /**
     * Get a new Arraylist of all the airlines with contains string in country
     * @param airportData data to filter through
     * @param contains substring to filter for
     * @return the airport data filtered by country containing contains
     */
    public ArrayList<Airport> filterByCountry(ArrayList<Airport> airportData, String contains) {
        return filterByAll(airportData, "", "", contains);
    }

    //TODO write error checking for filters making sure data is loaded.
    /**
     * Get a new Arraylist of all the airlines
     * Super filter method - no point repeating the same code in multiple methods
     * @param airportData data to filter through
     * @param nameContains substring to filter for in the airline names
     * @param cityContains substring to filter for in the airline aliases
     * @param countryContains substring to filter for in the airline countries
     * @return the airport data filtered by nameContains, aliasContains and countryContains
     */
    public ArrayList<Airport> filterByAll(ArrayList<Airport> airportData, String nameContains, String cityContains, String countryContains) {
        ArrayList<Airport> filteredAirports = new ArrayList<>();
        // Does at least one filter exist?
        if (!(nameContains.equals("") && cityContains.equals("") && countryContains.equals(""))) {
            for (Airport airport : airportData) {
                // Does the airport pass the filters?
                if (airport.getName().contains(nameContains) && airport.getCity().contains(cityContains) && airport.getCountry().contains(countryContains)) {
                    filteredAirports.add(airport);
                }
            }
        }
        return filteredAirports;
    }
}
