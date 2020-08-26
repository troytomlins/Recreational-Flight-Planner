package seng202.group10.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class FlightTest {

    @Test
    public void setLegDistancesTest() {
        Flight flight = new Flight();
        //Create various legs.
        FlightLeg leg0 = new FlightLeg("NZCH", "APT", -43.4866, 172.534, 0, 0);
        FlightLeg leg1 = new FlightLeg("CH", "VOR",	-43.5041,	172.515, 400, 0);
        FlightLeg leg2 = new FlightLeg("KADOM", "FIX", -33.7108, 150.3, 35000, 0);
        FlightLeg leg3 = new FlightLeg("WSSS", "APT", 1.35561, 103.988, 0, 0);

        //Add legs to flight.
        flight.addLeg(leg0, 0);
        flight.addLeg(leg1, 1);
        flight.addLeg(leg2, 2);
        flight.addLeg(leg3, 3);

        flight.setLegDistances(); // Calculate the leg distances and update them.
        flight.updateDistance(); // Adds together all the leg distances. Should equal legDistance.
        double legDistance = 8419.4342074466527;
        assertEquals(legDistance, flight.getTotalDistance(), 1);
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

        double legDistance = 8404.214781889239;
        assertEquals(legDistance, flight.calculateLegDistance(lat1, lat2, long1, long2, alt1, alt2), 1);
    }
}
