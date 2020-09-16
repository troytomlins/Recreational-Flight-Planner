package seng202.group10.controller.filters;

import seng202.group10.model.Airline;
import seng202.group10.model.AirlineRW;
import seng202.group10.model.Airport;

import java.util.ArrayList;

/**
 * Class to filter the airline data.
 * @Author Jonathon Howe
 * @Author Niko Tainui
 */
public class AirlineFilters {

    /**
     * Get a new Arraylist of all the airlines with contains string in name.
     * @param airlineData data to filter through
     * @param contains substring to filter for
     * @return the airline data filtered by names containing contains
     */
    public ArrayList<Airline> filterByName(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, contains, "", "");
    }

    /**
     * Get a new Arraylist of all the airlines with contains string in alias.
     * @param airlineData data to filter through
     * @param contains substring to filter for
     * @return the airline data filtered by alias containing contains
     */
    public ArrayList<Airline> filterByAlias(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, "", contains, "");

    }

    /**
     * Get a new Arraylist of all the airlines with contains string in country.
     * @param airlineData data to filter through
     * @param contains substring to filter for
     * @return the airline data filtered by countries containing contains
     */
    public ArrayList<Airline> filterByCountry(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, "", "", contains);
    }

    /**
     * Get a new Arraylist of all the airlines
     * Super filter method - no point repeating the same code in multiple methods
     * @param airlineData data to filter through
     * @param nameContains substring to filter for in the airline names
     * @param aliasContains substring to filter for in the airline aliases
     * @param countryContains substring to filter for in the airline countries
     * @return the airline data filtered by nameContains, aliasContains and countryContains
     */
    public ArrayList<Airline> filterByAll(ArrayList<Airline> airlineData, String nameContains, String aliasContains, String countryContains) {
        ArrayList<Airline> filteredAirlines = new ArrayList<>();
        // Does at least one filter exist?
        if (!(nameContains.equals("") && aliasContains.equals("") && countryContains.equals(""))) {
            for (Airline airline : airlineData) {
                // Does the airline pass the filters?
                if (airline.getName().contains(nameContains) && airline.getAlias().contains(aliasContains) && airline.getCountry().contains(countryContains)) {
                    filteredAirlines.add(airline);
                }
            }
        }
        return filteredAirlines;
    }
}