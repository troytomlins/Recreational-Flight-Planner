package seng202.group10.model;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

public class AirportTrafficModel {

    private static int TIME_RANGE = 500;
    private static final String apiUrlArrival = "https://opensky-network.org/api/flights/arrival";
    private static final String apiUrlDep = "https://opensky-network.org/api/flights/departure";

    private static HttpTransport TRANSPORT;

    private static HttpTransport transport() {
        if (null == TRANSPORT) {
            TRANSPORT = new NetHttpTransport();
        }
        return TRANSPORT;
    }

    private static HttpRequestFactory REQ_FACTORY;

    private static HttpRequestFactory reqFactory() {
        if (null == REQ_FACTORY) {
            REQ_FACTORY = transport().createRequestFactory();
        }
        return REQ_FACTORY;
    }

    /**
     * Returns the number of flights arround the airport at the sepcified time
     * @param airport
     * @param dateTimeStr
     * @return
     */
    public int getAirportTraffic(Airport airport, String dateTimeStr) throws IOException, java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");
        Instant instant = sdf.parse(dateTimeStr).toInstant();
        return getAllTraffic(airport.getIcao(), instant);
    }

    /**
     * Performs an http get request to specified url
     * @param reqUrl Url for http get rewuest
     * @param icao Airport icao
     * @param begin Being time
     * @param end End time
     * @return HttpResponse for flights in and
     * @throws IOException
     */
    private static HttpResponse getTrafficResponse(String reqUrl, String icao, String begin, String end) throws IOException {
        GenericUrl url = new GenericUrl(reqUrl);
        url.put("airport", icao);
        url.put("begin", begin);
        url.put("end", end);
        HttpRequest req = reqFactory().buildGetRequest(url);
        @SuppressWarnings("unused")
        HttpResponse resp = req.execute();
        return resp;
    }

    /**
     * Calculates how many json items were returned from an http request
     * @param resp Repsonse object
     * @return Integer count of how many items where in the json array
     */
    private static int getReturnCount(HttpResponse resp) throws IOException {
        JSONParser parser = new JSONParser();
        Object resultObject = null;
        try {
            resultObject = parser.parse(resp.parseAsString());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        JSONArray array=(JSONArray)resultObject;
        return array.size();
    }

    /**
     * Calculates how many flights are in and out of an airport 30 mins either side of the date
     * @param icao Icao of the airport to check
     * @param date Datetime to check for flights
     * @return Integer count of the in and out flights from the airport
     */
    private int getAllTraffic(String icao, Instant  date) throws IOException {
        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        long epoch = date.atZone(zoneId).toEpochSecond();
        long begin = epoch - (TIME_RANGE * 60);
        long end = epoch + (TIME_RANGE * 60);
        HttpResponse inResp;
        HttpResponse outResp;
        try {
            inResp = getTrafficResponse(apiUrlArrival, icao, "" + begin, "" + end);
            outResp = getTrafficResponse(apiUrlDep, icao, "" + begin, "" + end);
        } catch (HttpResponseException err) {
            return 0;
        }
        int in = getReturnCount(inResp);
        int out = getReturnCount(outResp);
        return in + out;
    }
}
