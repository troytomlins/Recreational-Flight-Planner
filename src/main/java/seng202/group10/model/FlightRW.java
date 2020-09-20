package seng202.group10.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FlightRW extends RWStream {

    public FlightRW(String inFile, String outFile) {
        super(inFile, outFile);
    }

    public FlightRW(String inFile) {
        super(inFile, "flights.csv");
    }

    public FlightRW() {
        super("flights.csv");
    }

    /**
     * Read the file at inFile and create a Flight object
     * @return Flight object
     * @throws IncompatibleFileException when a non csv file is given
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    public Flight readFlight() throws IOException, IncompatibleFileException {
        Flight flight = new Flight();

        // Initialise file reader and string row variable
        BufferedReader csvReader = new BufferedReader(new FileReader(super.getInFilename()));

        // Parse each line
        CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
        for (CSVRecord csvRecord : parser) {    // For each line
            try {
                // Make point from each line
                FlightPoint flightPoint = new FlightPoint(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        Double.parseDouble(csvRecord.get(2)),
                        Double.parseDouble(csvRecord.get(3)),
                        Double.parseDouble(csvRecord.get(4))
                );
                flight.addPoint(flightPoint);    // add point
            } catch (Exception e) {
                throw new IncompatibleFileException();
            }
        }

        // Close reader
        csvReader.close();
        return flight;
    }

    /**
     * @param args default command line args
     * @throws IncompatibleFileException when a non csv file is given
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    public static void main(String[] args) throws IOException, IncompatibleFileException {
        FlightRW test = new FlightRW();
        test.readFlight();
    }
}
