package seng202.group10.model;

import java.util.ArrayList;

/**
 * @Author Niko
 * @Author Zach
 */

public class FlightBuilder {

    private Flight flight;
    private Aircraft aircraft;
    private double totalDistance;
    private ArrayList<FlightLeg> legs = new ArrayList<FlightLeg>();

    public void instantiateFlight() {
        Flight flight = new Flight();
    }

    public void addLeg(FlightLeg leg, int index) {
        legs.add(index, leg);
    }

    public String updateDistance() {
        totalDistance = 0;
        for (FlightLeg leg : legs) {
            totalDistance = totalDistance + leg.getLegDistance();
        }

        if (checkHalfFlightLength(totalDistance, aircraft)) {
            return "Halfway through flight";
        } else if (checkFlightLength(totalDistance, aircraft)) {
            return "flight length exceeded max range of aircraft";
        } else {
            return "Updated Distance";
        }
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Loops through legs, setting the leg distances.
     */
    public void setLegDistances() {
        double latitudePrev = 0;
        double longitudePrev = 0;
        double altitudePrev = 0;
        double latitudeCurr = 0;
        double longitudeCurr = 0;
        double altitudeCurr = 0;
        double legDistance = 0;
        for (int i = 0; i < legs.size(); i++) {
            FlightLeg currentLeg = legs.get(i);
            latitudeCurr = currentLeg.getLatitude();
            longitudeCurr = currentLeg.getLongitude();
            altitudeCurr = currentLeg.getAltitude();
            if (i != 0) {
                FlightLeg previousLeg = legs.get(i - 1);
                latitudePrev = previousLeg.getLatitude();
                longitudePrev = previousLeg.getLongitude();
                altitudePrev = previousLeg.getAltitude();
            } else {
                latitudePrev = latitudeCurr;
                longitudePrev = longitudeCurr;
                altitudePrev = altitudeCurr;
            }
            legDistance = calculateLegDistance(latitudePrev, latitudeCurr, longitudePrev, longitudeCurr, altitudePrev, altitudeCurr);
            currentLeg.setLegDistance(legDistance);
        }

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

        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distance = radius * c;

        double height = (altitude1 - altitude2) / (3.2808 * 1000); // Height converted to km (an approximation);

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return (Math.sqrt(distance));
    }

    public boolean checkFlightLength(double distance, Aircraft aircraft) {
        /**
         * returns false when planned flight is less than aircraft range
         */
        return (aircraft.getRange() < distance);
    }

    public boolean checkHalfFlightLength(double distance, Aircraft aircraft) {
        /**
         * returns true when planned flight is over halfway through max aircraft range
         */
        return (aircraft.getRange() / 2 > distance);
    }
    public Flight outputFlight() {
        return flight;
    }
}
