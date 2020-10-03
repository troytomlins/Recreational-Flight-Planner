package seng202.group10.model;

import java.util.ArrayList;

/**
 * Class for defining a Flight.
 * @author Johnny Howe
 * @author Niko Tainui
 * @author Zach Kaye
 */

public class Flight {
    private double totalDistance;
    private Aircraft aircraft;      // null on commercial flights
    private ArrayList<FlightPoint> points;

    /**
     * Constructor for Flight.
     */
    public Flight() {
        totalDistance = 0;
        points = new ArrayList<>();
    }

    /**
     * Adds a new Point to flight.
     * @param newPoint FlightPoint
     */
    public void addPoint(FlightPoint newPoint) {
        points.add(newPoint);
        calculateDistance();
    }

    /**
     * Adds a new Point to flight at a certain index.
     * @param newPoint FlightPoint
     * @param index position to insert
     */
    public void addPoint(FlightPoint newPoint, int index) {
        points.add(index, newPoint);
        calculateDistance();
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public String getAircraftName() {return this.aircraft != null ? this.aircraft.getName() : null; }

    /**
     * Returns the flight start latitude point as a string
     * @return Start latitude point string.
     */
    public String getStartLatitudeString() {
        if (points.size() == 0) {
            return "";
        } else {
            return String.format("%.8f", points.get(0).latitude);
        }
    }

    /**
     * Returns the flight start longitude point as a string.
     * @return Start longitude point string.
     */
    public String getStartLongitudeString() {
        if (points.size() == 0) {
            return "";
        } else {
            return String.format("%.8f", points.get(0).longitude);
        }
    }

    /**
     * Returns the destination latitude point as a string.
     * @return Destination latitude point string.
     */
    public String getDestLatitudeString() {
        if (points.size() <= 1) {
            return "";
        } else {
            return String.format("%.8f", points.get(points.size() - 1).latitude);
        }
    }

    /**
     * Returns the destination longitude point as a string.
     * @return Destination longitude point string.
     */
    public String getDestLongitudeString() {
        if (points.size() <= 1) {
            return "";
        } else {
            return String.format("%.8f", points.get(points.size() - 1).longitude);
        }
    }

    /**
     * Returns the start coordinates as a string.
     * Formatted as "latitude, longitude"
     * @return Start coordinates string.
     */
    public String getStartCoordString() {
        return getStartLatitudeString() + ", " + getStartLongitudeString();
    }

    /**
     * Returns the destination coordinates as a string.
     * formatted as "latitude, longitude"
     * @return Start coordinates string.
     */
    public String getDestCoordString() {
        return getDestLatitudeString() + ", " + getDestLongitudeString();
    }

    /**
     * Returns the number of flight legs as a string.
     * @return Number of flight legs.
     */
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

    public ArrayList<FlightPoint> getFlightPoints() {
        return points;
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
        double latitude1 = point1.latitude;
        double longitude1 = point1.longitude;
        double latitude2 = point2.latitude;
        double longitude2 = point2.longitude;
        double altitude1 = point1.altitude;
        double altitude2 = point2.altitude;

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
     * Checks if the aircraft can successfully go the distance.
     * @return false if it can't
     */
    public boolean canFly() {
        if (aircraft == null) {
            return true;
        } else {
            return (aircraft.getRange() > totalDistance);
        }
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Flight(");
        for (FlightPoint point : getFlightPoints()) {
            s.append(point.toString()).append(", ");
        }
        s.append(")");
        return s.toString();
    }


}