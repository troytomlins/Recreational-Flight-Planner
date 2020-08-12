package seng202.group10.model;

public class FlightBuilder {

    private Flight flight;

    public void instantiateFlight() {
        Flight flight = new Flight();
    }

    public void addLeg(FlightLeg leg, int legIndex) {
        flight.addLeg(leg, legIndex);
    }

    public Flight outputFlight() {
        return flight;
    }
}
