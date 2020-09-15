package seng202.group10.controller.filters;

import seng202.group10.model.Airport;
import seng202.group10.model.AirportRW;
import seng202.group10.model.DatabaseConnection;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Niko Tainui
 */

public class AirportFilters {

    private FilterSender filterSender;

    public AirportFilters() {
        filterSender = new FilterSender();
        filterSender.setTableName("airports");
    }

    public void addFilter(String columnName, String pattern) {
        if (pattern != "") {
            filterSender.addFilter(columnName, pattern);
        }
    }

    public ArrayList<Airport> applyFilters() {
        ArrayList<Airport> resultAirports = new ArrayList<Airport>();

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
