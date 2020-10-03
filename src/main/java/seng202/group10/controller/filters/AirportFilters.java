package seng202.group10.controller.filters;

import seng202.group10.model.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to filter Airport data.
 * @author Niko Tainui
 * @author Mitchell Freeman
 */

public class AirportFilters extends GenericFilters {

    /**
     * Constructor for AirportFilters.
     * Sets table to "airports".
     */
    public AirportFilters() {
        super("airports");
    }

    /**
     * Apply filters and returns list of Airports fitting applied conditions.
     * @return ArrayList of Airport
     */
    public ArrayList<Airport> applyFilters() {
        ArrayList<Airport> resultAirports = new ArrayList<>();

        ResultSet results = filterSender.applyFilter();
        try {
            while (results.next()) {
                resultAirports.add(new Airport(
                        results.getString("name"),
                        results.getString("city"),
                        results.getString("country"),
                        results.getString("iata"),
                        results.getString("icao"),
                        results.getDouble("latitude"),
                        results.getDouble("longitude"),
                        results.getFloat("altitude"),
                        results.getFloat("timezone"),
                        results.getString("dstType"),
                        results.getString("tzDatabase")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultAirports;
    }
}
