package seng202.group10.controller.filters;

import seng202.group10.model.Airline;
import seng202.group10.model.AirlineRW;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Jonathon Howe
 * @Author Niko Tainui
 * @Author Mitchell Freeman
 */
public class AirlineFilters extends GenericFilters {
    public AirlineFilters() {
        super("airlines");
    }

    public ArrayList<Airline> applyFilters() {
        ArrayList<Airline> resultAirlines = new ArrayList<Airline>();

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