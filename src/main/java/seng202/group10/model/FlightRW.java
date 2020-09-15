package seng202.group10.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FlightRW extends RWStream {

    public FlightRW(String inFile) {
        super(inFile, "flights.csv");
    }

    public FlightRW() {
        super("flights.csv");
    }
//    public ArrayList<Flight> readFlights() throws IOException, IncompatibleFileException {
//        ArrayList<Flight> flights = new ArrayList<>();
//
//        // Initialise file reader and string row variable
//        BufferedReader csvReader = new BufferedReader(new FileReader(super.getInFilename()));
//
//        // Parse each line
//        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
//        for (CSVRecord csvRecord : parser) {
//            try {
//
//                // Get corresponding values from the csv record
//                String SrcAirportName = csvRecord.get(1);
//                String city = csvRecord.get(2);
//                String country = csvRecord.get(3);
//                String iata = csvRecord.get(4);
//                String icao = csvRecord.get(5);
//                double latitude = Double.parseDouble(csvRecord.get(6));
//                double longitude = Double.parseDouble(csvRecord.get(7));
//                float altitude = Float.parseFloat(csvRecord.get(8));
//                float timezone = Float.parseFloat(csvRecord.get(9));
//                String dstType = csvRecord.get(10);
//                String tzDatabase = csvRecord.get(11);
//
//                // Create airline and add to model
//                Airport airport = new Airport(name, city, country, iata, icao, latitude, longitude, altitude, timezone, dstType, tzDatabase);
//                airports.add(airport);
//            } catch (Exception e) {
//                throw new IncompatibleFileException();
//            }
//        }
//
//        // Close reader
//        csvReader.close();
//        return airports;
//    }
}
