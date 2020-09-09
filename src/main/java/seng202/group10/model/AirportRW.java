package seng202.group10.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Mitchell
 */
public class AirportRW extends RWStream {


    public AirportRW(String inFile) {
        super(inFile, "airport.csv");
    }

    public AirportRW() {
        super("airport.csv");
    }

    public ArrayList<Airport> readAirports() throws IOException, IncompatibleFileException {
        ArrayList<Airport> airports = new ArrayList<>();

        // Initialise file reader and string row variable
        BufferedReader csvReader = new BufferedReader(new FileReader(super.getInFilename()));

        // Parse each line
        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {
            try {

                // Get corresponding values from the csv record
                String name = csvRecord.get(1);
                String city = csvRecord.get(2);
                String country = csvRecord.get(3);
                String iata = csvRecord.get(4);
                String icao = csvRecord.get(5);
                double latitude = Double.parseDouble(csvRecord.get(6));
                double longitude = Double.parseDouble(csvRecord.get(7));
                float altitude = Float.parseFloat(csvRecord.get(8));
                float timezone = Float.parseFloat(csvRecord.get(9));
                String dstType = csvRecord.get(10);
                String tzDatabase = csvRecord.get(11);

                // Create airline and add to model
                Airport airport = new Airport(name, city, country, iata, icao, latitude, longitude, altitude, timezone, dstType, tzDatabase);
                airports.add(airport);
            } catch (Exception e) {
                throw new IncompatibleFileException();
            }
        }

        // Close reader
        csvReader.close();
        return airports;
    }

    public void writeAirports(ArrayList<Airport> airports) {
        ArrayList<ArrayList<String>> airportStrings = new ArrayList<ArrayList<String>>();

        for (Airport airport: airports) {
            airportStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            airport.getName(),
                            airport.getCity(),
                            airport.getCountry(),
                            airport.getIata(),
                            airport.getIcao(),
                            Double.toString(airport.getLatitude()),
                            Double.toString(airport.getLongitude()),
                            Float.toString(airport.getAltitude()),
                            Float.toString(airport.getTimezone()),
                            airport.getDstType(),
                            airport.getTzDatabase()
            )));
        }
        writeAll(airportStrings);
    }
}
