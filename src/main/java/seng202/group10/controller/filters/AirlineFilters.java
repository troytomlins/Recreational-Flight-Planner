package seng202.group10.controller.filters;

import seng202.group10.model.Airline;
import seng202.group10.model.AirlineRW;

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
        ArrayList<Airline> filteredAirlines = new ArrayList<>();
        for (Airline airline : airlineData) {
            if (airline.getName().contains(contains)) {
                filteredAirlines.add(airline);
            }
        }
        return filteredAirlines;
    }

    public ArrayList<Airline> filterByAlias(ArrayList<Airline> airlineData, String contains) {
        ArrayList<Airline> filteredAirlines = new ArrayList<>();
        for (Airline airline : airlineData) {
            if (airline.getAlias().contains(contains)) {
                filteredAirlines.add(airline);
            }
        }
        return filteredAirlines;
    }

    public ArrayList<Airline> filterByCallsign(ArrayList<Airline> airlineData, String contains) {
        ArrayList<Airline> filteredAirlines = new ArrayList<>();
        for (Airline airline : airlineData) {
            if (airline.getCallsign().contains(contains)) {
                filteredAirlines.add(airline);
            }
        }
        return filteredAirlines;
    }





//    public static void main(String[] args) {
//        AirlineRW rw = new AirlineRW();
//        ArrayList<Airline> airlines = rw.readAirlines();
//
//        AirlineFilters airlineFilters = new AirlineFilters();
//
//        ArrayList<Airline> filtered = airlineFilters.filterByName(airlines, "Blue");
//        for (Airline airline : filtered) {
//            System.out.println(airline);
//        }
//    }
}