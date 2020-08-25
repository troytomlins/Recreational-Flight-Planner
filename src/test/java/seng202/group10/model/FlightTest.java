package seng202.group10.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class FlightTest {

    @Test
    public void setLegDistancesTest() {

    }

    @Test
    public void calculateLegDistanceTest() {
        Flight flight = new Flight();

        //Latitude, Longitude and Altitude values for Christchurch Airport.
        double lat1 = -43.4866;
        double long1 = 172.534;
        double alt1 = 0;
        //Latitude, Longitude and Altitude values for Singapore Changi Airport.
        double lat2 = 1.35561;
        double long2 = 103.988;
        double alt2 = 0;

        double legDistance = 0;
        assertEquals(legDistance, flight.calculateLegDistance(lat1, lat2, long1, long2, alt1, alt2), 1);
    }
}
