package seng202.group10.model;

public class Aircraft {
    private String iata;
    private String name;
    private String icao;
    private double fuelRate;

    public Aircraft(String iata, String name, String icao, double fuelRate) {
        this.iata = iata;
        this.name = name;
        this.icao = icao;
        this.fuelRate = fuelRate;
    }

    public String getIata() {
        return iata;
    }

    public String getName() {
        return name;
    }

    public String getIcao() {
        return icao;
    }

    public double getFuelRate() {
        return fuelRate;
    }
}
