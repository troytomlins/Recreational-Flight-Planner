package seng202.group10.controller.filters;

import seng202.group10.model.Airline;
import seng202.group10.model.AirlineRW;
import seng202.group10.model.Airport;

import java.util.ArrayList;

/**
 * @Author Jonathon Howe
 * @Author Niko Tainui
 */
public class AirlineFilters {

    /**
     * Get a new Arraylist of all the airlines with contains string in name.
     * @param contains (String): substring to filter for
     * @return ArrayList<Airline>
     */
    public ArrayList<Airline> filterByName(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, contains, "", "");
    }

    /**
     * Get a new Arraylist of all the airlines with contains string in alias.
     * @param contains (String): substring to filter for
     * @return ArrayList<Airline>
     */
    public ArrayList<Airline> filterByAlias(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, "", contains, "");

    }

    /**
     * Get a new Arraylist of all the airlines with contains string in country.
     * @param contains (String): substring to filter for
     * @return ArrayList<Airline>
     */
    public ArrayList<Airline> filterByCountry(ArrayList<Airline> airlineData, String contains) {
        return filterByAll(airlineData, "", "", contains);
    }

    /**
     * Get a new Arraylist of all the airlines
     * Super filter method - no point repeating the same code in multiple methods
     * @param nameContains (String): substring to filter for in the airline names
     * @param aliasContains (String): substring to filter for in the airline aliases
     * @param countryContains (String): substring to filter for in the airline countries
     * @return ArrayList<Airline>
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