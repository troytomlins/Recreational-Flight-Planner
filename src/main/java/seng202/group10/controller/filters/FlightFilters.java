package seng202.group10.controller.filters;

import seng202.group10.model.*;

import java.util.ArrayList;

//TODO write the filters depending on GUI
/**
 * Class to filter the airline data.
 * @author Niko Tainui
 * @author Johnny Howe
 */

// TODO what should it filter?
public class FlightFilters {
//
//    // TODO what does this do - put in comment pls
//    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
//
//    /**
//     * Get a new Arraylist of all the airlines with contains string in name.
//     * @param flightData data to filter through
//     * @param contains substring to filter for
//     * @return the airline data filtered by names containing contains
//     */
//    public ArrayList<Airline> filterByName(ArrayList<Flight> flightData, String contains) {
//        return filterByAll(flightData, contains, "", "");
//    }
//
//    /**
//     * Get a new Arraylist of all the airlines with contains string in alias.
//     * @param flightData data to filter through
//     * @param contains substring to filter for
//     * @return the airline data filtered by alias containing contains
//     */
//    public ArrayList<Airline> filterByAlias(ArrayList<Flight> flightData, String contains) {
//        return filterByAll(flightData, "", contains, "");
//
//    }
//
//    /**
//     * Get a new Arraylist of all the airlines with contains string in country.
//     * @param flightData data to filter through
//     * @param contains substring to filter for
//     * @return the airline data filtered by countries containing contains
//     */
//    public ArrayList<Airline> filterByCountry(ArrayList<Flight> flightData, String contains) {
//        return filterByAll(flightData, "", "", contains);
//    }
//
//    /**
//     * Get a new Arraylist of all the airlines
//     * Super filter method - no point repeating the same code in multiple methods
//     * @param flightData data to filter through
//     * @param nameContains substring to filter for in the airline names
//     * @param aliasContains substring to filter for in the airline aliases
//     * @param countryContains substring to filter for in the airline countries
//     * @return the airline data filtered by nameContains, aliasContains and countryContains
//     */
//    public ArrayList<Airline> filterByAll(ArrayList<Flight> flightData, String nameContains, String aliasContains, String countryContains) {
//        ArrayList<Airline> filteredAirlines = new ArrayList<>();
//        // Does at least one filter exist?
//        if (!(nameContains.equals("") && aliasContains.equals("") && countryContains.equals(""))) {
//            for (Flight flight : flightData) {
//                // Does the airline pass the filters?
//                if (flight.getName().contains(nameContains) && flight.getAlias().contains(aliasContains) && flight.getCountry().contains(countryContains)) {
//                    filteredAirlines.add(flight);
//                }
//            }
//        }
//        return filteredAirlines;
//    }
}
