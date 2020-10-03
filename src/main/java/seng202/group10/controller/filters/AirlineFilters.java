package seng202.group10.controller.filters;

import seng202.group10.model.Airline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to filter Airline data.
 * @author Johnny Howe
 * @author Niko Tainui
 * @author Mitchell Freeman
 */
public class AirlineFilters extends GenericFilters {

    /**
     * Constructor for AirlineFilters.
     * Sets table to "airlines".
     */
    public AirlineFilters() {
        super("airlines");
    }

    /**
     * Apply filters and returns list of Airlines fitting applied conditions.
     * @return ArrayList of Airline
     */
    public ArrayList<Airline> applyFilters() {
        ArrayList<Airline> resultAirlines = new ArrayList<>();

        ResultSet results = filterSender.applyFilter();
        try {
            while (results.next()) {
                resultAirlines.add(new Airline(
                        results.getString("name"),
                        results.getString("alias"),
                        results.getString("iata"),
                        results.getString("icao"),
                        results.getString("callsign"),
                        results.getString("country")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultAirlines;
    }
}