package seng202.group10.model;

import java.util.ArrayList;

public class Flight {
    private ArrayList<FlightLeg> legs;
    private double totalDistance;

    public void addLeg(FlightLeg leg, int index) {
        legs.add(index, leg);
    }

    public void updateDistance() {
        totalDistance = 0;
        for (FlightLeg leg: legs) {
            totalDistance = totalDistance + leg.getLegDistance();
        }
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setLegDistance() {
        double latitudePrev = 0;
        double longitudePrev = 0;
        double altitudePrev = 0;
        double latitudeCurr = 0;
        double longitudeCurr = 0;
        double altitudeCurr = 0;
        double legDistance = calculateLegDistance(latitudePrev, latitudeCurr, longitudePrev, longitudeCurr, altitudePrev, altitudeCurr);
    }

    /**
     * Calculates the Great-circle distance between two points.
     * This distance is calculated using the Haversine formula:
     * a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
     * c = 2 ⋅ atan2( √a, √(1−a) )
     * d = R ⋅ c
     * where φ is latitude, λ is longitude, R is earth’s radius and all angles are in radians.
     *
     * @param latitude1 - The latitude of the previous point.
     * @param latitude2 - The latitude of the current point.
     * @param longitude1 - The longitude of the previous point.
     * @param longitude2 - The longitude of the current point
     * @param altitude1 - The altitude of the previous point in ft.
     * @param altitude2 - The altitude of the current point in ft.
     * @return double distance - The calculated Great-circle distance.
     */
    public double calculateLegDistance(double latitude1, double latitude2, double longitude1, double longitude2, double altitude1, double altitude2) {
        double distance = 0;

        final int radius = 6371; // Radius of Earth in km.

        double latitudeDistance = Math.toRadians(latitude2 - latitude1);
        double longitudeDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.pow(Math.sin(latitudeDistance / 2), 2) + (Math.cos(Math.toRadians(latitude1)) *
                Math.cos(Math.toRadians(latitude2) * Math.pow(Math.sin(longitudeDistance / 2), 2)));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distance = radius * c * 1000; // Distance converted to meters.

        double height = (altitude2 - altitude1) / 3.2808; // Height converted to meters (an approximation);

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);

    }

}
