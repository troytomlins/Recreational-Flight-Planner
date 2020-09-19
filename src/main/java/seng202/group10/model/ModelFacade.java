package seng202.group10.model;

public class ModelFacade {

    private RouteModel routeModel;
    private AirportModel airportModel;
    private FlightModel flightModel;
    private AircraftModel aircraftModel;
    private AirlineModel airlineModel;

    //private MainGuiFacade GUI;
    //private MapConnection map;


    public RouteModel getRouteModel() {
        return routeModel;
    }

    public AirportModel getAirportModel() {
        return airportModel;
    }

    public FlightModel getFlightModel() {
        return flightModel;
    }

    public AircraftModel getAircraftModel() {
        return aircraftModel;
    }

    public AirlineModel getAirlineModel() {
        return airlineModel;
    }
}
