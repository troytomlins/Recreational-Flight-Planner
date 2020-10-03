package seng202.group10.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Read / Write Class for Flights.
 */
public class FlightRW extends RWStream {

    /**
     * Constructor for FlightRW with in and out file.
     * @param inFile in file
     * @param outFile out file
     */
    public FlightRW(String inFile, String outFile) {
        super(inFile, outFile);
    }

    /**
     * Constructor for FlightRW with an in file.
     * @param inFile file
     */
    public FlightRW(String inFile) {
        super(inFile, "flights.csv");
    }

    /**
     * Stand-alone Constructor for FlightRW.
     */
    public FlightRW() {
        super("flights.csv");
    }

    /**
     * Read the file at inFile and create a Flight object
     * @return Flight object
     * @throws IOException IO Exception
     * @throws IncompatibleFileException Incompatible File
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

    public static void main(String[] args) throws IOException, IncompatibleFileException {
        FlightRW test = new FlightRW();
        test.readFlight();
    }

    /**
     * Uses the super class RWStream to write a flight to a file.
     * The Flight object attributes have to be converted to strings.
     * @param flight A Flight object which contains points.
     */
    public void writeFlight(Flight flight) {
        ArrayList<ArrayList<String>> flightArr = new ArrayList<ArrayList<String>>();

        for (FlightPoint point: flight.getFlightPoints()) {
            flightArr.add(
                    new ArrayList<String>(Arrays.asList(
                            point.type,
                            point.id,
                            Double.toString(point.altitude),
                            Double.toString(point.latitude),
                            Double.toString(point.longitude)
                    )));
        }
        writeAll(flightArr);
    }
}
