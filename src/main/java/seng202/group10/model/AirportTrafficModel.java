package seng202.group10.model;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class AirportTrafficModel {

    private static final String apiUrl = "";

    public int getAirportTraffic(Airport airport, String dateTimeStr) {
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(dateTimeStr);

    }

}
