package seng202.group10.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class AirportTest {
    private Airport airport;

    @Test
    public void getAirport() {
        airport = new Airport("Christchurch Intl", "Christchurch", "New Zealand", "CHC", "NZCH", -43.489358, 172.532225, 123, 12, "Z", "Pacific/Auckland");
        assertEquals(airport.getCity(), "Christchurch");
    }
}
