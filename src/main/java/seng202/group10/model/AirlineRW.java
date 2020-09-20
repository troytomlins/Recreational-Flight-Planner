package seng202.group10.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Mitchell Freeman
 */
public class AirlineRW extends RWStream {
    /**
     * creates instance of class with a default outfile
     * @param inFile
     */
    public AirlineRW(String inFile) {
        super(inFile, "airline.csv");
    }

    public AirlineRW() {
        super("airline.csv");
    }

    public ArrayList<Airline> readAirlines() throws IncompatibleFileException, FileFormatException {
        return readAirlines(new ArrayList<Integer>());
    }

    /**
     * Read airlines from file
     * @param ignoreLines List of lines index's to ignore (1 origin)
     * @return Arraylist of airlines read from file
     * @throws IncompatibleFileException
     * @throws FileFormatException
     */
    public ArrayList<Airline> readAirlines(ArrayList<Integer> ignoreLines) throws IncompatibleFileException, FileFormatException {
        // Initialise file reader and airports list
        ArrayList<Airline> airlines = new ArrayList<>();
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

            try {
                for (CSVRecord csvRecord : parser) {

                    // Check if the line is to be ignored
                    if (!ignoreLines.contains(lineNum)) {
                        try {
                            // Get corresponding values from the csv record
                            String name = csvRecord.get(1);
                            String alias = csvRecord.get(2);
                            String iata = csvRecord.get(3);
                            String icao = csvRecord.get(4);
                            String callsign = csvRecord.get(5);
                            String country = csvRecord.get(6);

                            // Create airline and add to model
                            Airline airline = new Airline(name, alias, iata, icao, callsign, country);
                            airlines.add(airline);

                        } catch (Exception e) {

                            // There was an error parsing the line, add to errorLines
                            errorLines.add(lineNum);
                        }
                    }
                    // Increment line counter
                    lineNum++;
                }
            } catch (IllegalStateException err) {
                throw new IncompatibleFileException();
            }

            // Deal with any errors
            if (errorLines.size() == (lineNum-1)) {
                // Every single line caused an error
                throw new IncompatibleFileException();
            } else if (errorLines.size() > 0) {
                // Some of the lines had errors
                throw new FileFormatException(errorLines, super.getInFilename());
            }

            return airlines;
        } catch (IOException e) {
            throw new IncompatibleFileException();
        } finally {
            // Close reader
            try {
                csvReader.close();
            } catch (IOException ignored) {}
        }
    }

    /**
     * Reads airlines from database
     * @return ArrayList of Airlines
     */
    public ArrayList<Airline> readDatabaseAirlines() {
        ResultSet results = databaseConnection.executeQuery("SELECT * FROM airlines");

        ArrayList<Airline> output = new ArrayList<Airline>();

        try {
            while (results.next()) {
                output.add(new Airline(
                        results.getString("name"),
                        results.getString("alias"),
                        results.getString("iata"),
                        results.getString("icao"),
                        results.getString("callsign"),
                        results.getString("country")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * Writes airlines to database
     * @param airlines
     */
    public void writeDatabaseAirlines(ArrayList<Airline> airlines) {
        databaseConnection.setAutoCommit(false);
        for (int i = 0; i < airlines.size(); i++) {
            try {
                PreparedStatement pStatement = databaseConnection.getFormattedPreparedStatement(
                        "INSERT INTO airlines (name, alias, iata, icao, callsign, country)",
                        6
                );
                Airline airline = airlines.get(i);
                pStatement.setString(1, airline.getName());
                pStatement.setString(2, airline.getAlias());
                pStatement.setString(3, airline.getIata());
                pStatement.setString(4, airline.getIcao());
                pStatement.setString(5, airline.getCallsign());
                pStatement.setString(6, airline.getCountry());


                pStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        databaseConnection.commit();
        databaseConnection.setAutoCommit(true);
    }
}
