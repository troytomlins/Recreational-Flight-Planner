package seng202.group10.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class FlightBuilderTest {

    @Test
    public void setLegDistancesTest() {
        FlightBuilder flightBuilder = new FlightBuilder();

        //Create various legs.
        FlightLeg leg0 = new FlightLeg("NZCH", "APT", -43.4866, 172.534, 0, 0);
        FlightLeg leg1 = new FlightLeg("CH", "VOR",	-43.5041,	172.515, 400, 0);
        FlightLeg leg2 = new FlightLeg("KADOM", "FIX", -33.7108, 150.3, 35000, 0);
        FlightLeg leg3 = new FlightLeg("WSSS", "APT", 1.35561, 103.988, 0, 0);

        //Add legs to flight.
        flightBuilder.addLeg(leg0, 0);
        flightBuilder.addLeg(leg1, 1);
        flightBuilder.addLeg(leg2, 2);
        flightBuilder.addLeg(leg3, 3);

        flightBuilder.setLegDistances(); // Calculate the leg distances and update them.
        flightBuilder.updateDistance(); // Adds together all the leg distances. Should equal legDistance.
        double legDistance = 8419.4342074466527;
        assertEquals(legDistance, flightBuilder.getTotalDistance(), 1);
    }


    @Test
    public void calculateLegDistanceTest() {
        FlightBuilder builder = new FlightBuilder();

        //Latitude, Longitude and Altitude values for Christchurch Airport.
        double lat1 = -43.4866;
        double long1 = 172.534;
        double alt1 = 0;
        //Latitude, Longitude and Altitude values for Singapore Changi Airport.
        double lat2 = 1.35561;
        double long2 = 103.988;
        double alt2 = 0;

        double legDistance = 8404.214781889239;
        assertEquals(legDistance, builder.calculateLegDistance(lat1, lat2, long1, long2, alt1, alt2), 1);
    }

}
