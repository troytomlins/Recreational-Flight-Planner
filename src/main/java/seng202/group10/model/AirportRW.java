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

/**
 * Read/Write Class for Airport.
 * @author Mitchell Freeman, Tom Rizzi
 */
public class AirportRW extends RWStream {


    public AirportRW(String inFile) {
        super(inFile, "airport.csv");
    }

    public AirportRW() {
        super("airport.csv");
    }

    /**
     * Parses an airports data file
     * @param ignoreLines List of line indices to ignore (1 origin)
     * @return Arraylist of airports read from the in file
     * @throws IncompatibleFileException
     * @throws FileFormatException
     */
    public ArrayList<Airport> readAirports(ArrayList<Integer> ignoreLines) throws IncompatibleFileException, FileFormatException {
        // Initialise file reader and airports list
        ArrayList<Airport> airports = new ArrayList<>();
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(super.getInFilename()));
        } catch (Exception e) {
            throw new IncompatibleFileException();
        }

        try {
            // Parse each line
            CSVParser parser = CSVParser.parse(csvReader, CSVFormat.EXCEL);
            Integer lineNum = 1;
            ArrayList<Integer> errorLines = new ArrayList<>();

            for (CSVRecord csvRecord : parser) {

                // Check if the line is to be ignored
                if (!ignoreLines.contains(lineNum)) {
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

                        // There was an error parsing the line, add to errorLines
                        errorLines.add(lineNum);
                    }
                }
                // Increment line counter
                lineNum++;
            }

            // Deal with any errors
            if (errorLines.size() == (lineNum-1)) {
                // Every single line caused an error
                throw new IncompatibleFileException();
            } else if (errorLines.size() > 0) {
                // Some of the lines had errors
                throw new FileFormatException(errorLines, super.getInFilename());
            }

            return airports;
        } catch (IOException | IllegalStateException e) {
            throw new IncompatibleFileException();
        } finally {
            // Close reader
            try {
                csvReader.close();
            } catch (IOException ignored) {}
        }
    }

    /**
     * Parses all lines from an airports data file
     * @return Arraylist of airports read from the in file
     * @throws IncompatibleFileException
     * @throws FileFormatException
     */
    public ArrayList<Airport> readAirports() throws IncompatibleFileException, FileFormatException {
        return readAirports(new ArrayList<>());
    }

    /**
     * reads airports from the database
     * @return ArrayList of Airport objects
     */
    public ArrayList<Airport> readDatabaseAirports() {
        ResultSet results = databaseConnection.executeQuery("SELECT * FROM airports");

        ArrayList<Airport> output = new ArrayList<>();

        try {
            if (results != null) {
                while (results.next()) {
                    output.add(new Airport(
                            results.getString("name"),
                            results.getString("city"),
                            results.getString("country"),
                            results.getString("iata"),
                            results.getString("icao"),
                            results.getDouble("latitude"),
                            results.getDouble("longitude"),
                            results.getFloat("altitude"),
                            results.getFloat("timezone"),
                            results.getString("dstType"),
                            results.getString("tzDatabase")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * writes airports to database
     * @param airports ArrayList of Airports
     */
    public void writeDatabaseAirports(ArrayList<Airport> airports) {
        databaseConnection.setAutoCommit(false);
        for (int i = 0; i < airports.size(); i++) {
            try {
                PreparedStatement pStatement = databaseConnection.getFormattedPreparedStatement(
                        "INSERT INTO airports (name, city, country, iata, icao, latitude, longitude, altitude, timezone, dstType, tzDatabase)",
                        11
                );
                Airport airport = airports.get(i);
                pStatement.setString(1, airport.getName());
                pStatement.setString(2, airport.getCity());
                pStatement.setString(3, airport.getCountry());
                pStatement.setString(4, airport.getIata());
                pStatement.setString(5, airport.getIcao());
                pStatement.setDouble(6, airport.getLatitude());
                pStatement.setDouble(7, airport.getLongitude());
                pStatement.setFloat(8, airport.getAltitude());
                pStatement.setFloat(9, airport.getTimezone());
                pStatement.setString(10, airport.getDstType());
                pStatement.setString(11, airport.getTzDatabase());

                pStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        databaseConnection.commit();
        databaseConnection.setAutoCommit(true);
    }

    /**
     * Deletes an aircraft from the database
     * @param airport Aircraft to be deleted
     */
    public void deleteDatabaseAirports(Airport airport) {
        String sql = "DELETE FROM airports WHERE name = '%s' AND city = '%s' AND country = '%s' AND icao = '%s'";
        sql = String.format(sql, airport.getName(), airport.getCity(), airport.getCity(), airport.getIata(), airport.getIcao());
        databaseConnection.executeStatement(sql);
    }
}
