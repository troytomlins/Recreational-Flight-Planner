package seng202.group10.model;

import java.util.ArrayList;

public class Flight {
    private ArrayList<FlightLeg> legs;
    private float totalDistance;

    public void addLeg(FlightLeg leg, int index) {
        legs.add(index, leg);
    }

    public void updateDistance() {
        totalDistance = 0;
        for (FlightLeg leg: legs) {
            totalDistance = totalDistance + leg.getLegDistance();
        }
    }

    public float getTotalDistance() {
        return totalDistance;
    }
}
