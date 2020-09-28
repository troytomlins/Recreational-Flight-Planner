package seng202.group10.model;

import java.util.ArrayList;

/**
 * @author Johnny Howe
 * @author Niko Tainui
 * @author Zach Kaye
 */

public class Flight {
    private double totalDistance;
    private Aircraft aircraft;      // null on commercial flights

    private ArrayList<FlightPoint> points;

    public Flight() {
        totalDistance = 0;
        points = new ArrayList<>();
    }

    public void addPoint(FlightPoint newPoint) {
        points.add(newPoint);
        calculateDistance();
    }

    public void addPoint(FlightPoint newPoint, int index) {
        points.add(index, newPoint);
        calculateDistance();
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public String getAircraftName() {return this.aircraft != null ? this.aircraft.getName() : null; }

    public String getStartLatitudeString() {
        if (points.size() == 0) {
            return "";
        } else {
            return String.format("%.8f", points.get(0).getLatitude());
        }
    }

    public String getStartLongitudeString() {
        if (points.size() == 0) {
            return "";
        } else {
            return String.format("%.8f", points.get(0).getLongitude());
        }
    }

    public String getDestLatitudeString() {
        if (points.size() <= 1) {
            return "";
        } else {
            return String.format("%.8f", points.get(points.size() - 1).getLatitude());
        }
    }

    public String getDestLongitudeString() {
        if (points.size() <= 1) {
            return "";
        } else {
            return String.format("%.8f", points.get(points.size() - 1).getLongitude());
        }
    }

    public String getStartCoordString() {
        return getStartLatitudeString() + ", " + getStartLongitudeString();
    }

    public String getDestCoordString() {
        return getDestLatitudeString() + ", " + getDestLongitudeString();
    }

    public String getLegCount() {
        return "" + (points.size() - 1);
    }

    /**
     * Sets totalDistance attribute
     * Sum of distances of legs
     */
    private void calculateDistance() {
        double total = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            FlightPoint current = points.get(i);
            FlightPoint next = points.get(i + 1);
            total += getLegDistance(current, next);
        }
        totalDistance = total;
    }

    /**
     * Calculates the Great-circle distance between two points.
     * This distance is calculated using the Haversine formula:
     * a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
     * c = 2 ⋅ atan2( √a, √(1−a) )
     * d = R ⋅ c
     * where φ is latitude, λ is longitude, R is earth’s radius and all angles are in radians.
     *
     * @param point1 - 1st flight point
     * @param point2 - 2nd flight point
     * @return distance - The calculated Great-circle distance.
     */
    public double getLegDistance(FlightPoint point1, FlightPoint point2) {
        double latitude1 = point1.getLatitude();
        double longitude1 = point1.getLongitude();
        double latitude2 = point2.getLatitude();
        double longitude2 = point2.getLongitude();
        double altitude1 = point1.getAltitude();
        double altitude2 = point2.getAltitude();

        final int radius = 6371; // Radius of Earth in km.
        double latitudeDistance = Math.toRadians(latitude2 - latitude1);
        double longitudeDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;
        double height = (altitude1 - altitude2) / (3.2808 * 1000); // Height converted to km (an approximation);
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    /**
     * @return can the aircraft go the distance?
     */
    public boolean checkFlightLength(double distance, Aircraft aircraft) {
        // TODO should this be here?
        return (aircraft.getRange() < distance);
    }

    public boolean checkHalfFlightLength(double distance, Aircraft aircraft) {
        // TODO does this need to exists - if so, here?
        return false;
    }

    public String getDistanceMessage() {
        // TODO decide if should be here or not
        return "TODO";
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }



}