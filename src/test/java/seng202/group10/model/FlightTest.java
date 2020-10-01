package seng202.group10.model;

import org.junit.Before;
import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class FlightTest {

    private Flight flight;
    private Flight compareFlight;

    @Before
    public void init() {
        compareFlight = new Flight();
        flight = new Flight();
    }

    @Test
    public void calculateDistanceTest() {
        FlightPoint point = new FlightPoint("APT","NZCH",0,-43.4866,172.534);
        FlightPoint point2 = new FlightPoint("VOR","CH",400,-43.5041,172.515);
        flight.addPoint(point);
        flight.addPoint(point2);
        long expected = Math.round(2.4799); // calculated distance between the two points
        long actual = Math.round(flight.getTotalDistance());
        assertEquals(expected,actual);
    }

    @Test
    public void getLegDistanceTest() {
        FlightPoint point = new FlightPoint("APT","NZCH",0,-43.4866,172.534);
        FlightPoint point2 = new FlightPoint("VOR","CH",400,-43.5041,172.515);
        long expected = Math.round(2.4799); // calculated distance between the two points
        long actual = Math.round(flight.getLegDistance(point, point2));
        assertEquals(expected,actual);
    }

}