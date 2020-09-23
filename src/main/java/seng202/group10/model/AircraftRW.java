package seng202.group10.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Read/Write Class for Aircraft.
 * @author Niko Tainui
 */

public class AircraftRW extends RWStream {

    /**
     * creates Aircraft read writer with a defined infile and a default outfile
     * @param inFile inFile name
     */
    public AircraftRW(String inFile) {
        super(inFile, "aircraft.csv");
    }

    /**
     * creates Aircraft read writer with a default infile
     */
    public AircraftRW() {
        super("aircraft.csv");
    }

    /**
     * reads the file output of a aircraft datasheet
     * @return list of Aircraft objects
     */
    public ArrayList<Aircraft> readAircraft() {
        ArrayList<ArrayList<String>> data = read();

        ArrayList<Aircraft> aircraftList = new ArrayList<>();

        for (ArrayList<String> dataLine: data) {
            if (true/*ValidateData.validateAircraft(dataLine)*/) {
                Aircraft aircraft = new Aircraft(
                        //Iata
                        dataLine.get(0),
                        //Name
                        dataLine.get(1),
                        //Icao
                        dataLine.get(2),
                        //Range
                        Double.parseDouble(dataLine.get(3))
                );
                aircraftList.add(aircraft);
            }
        }
        return aircraftList;
    }

    /**
     * takes aircraft objects as an arraylist and writes it to the outfile
     * @param aircrafts ArrayList of Aircraft
     */
    public void writeAircrafts(ArrayList<Aircraft> aircrafts) {
        ArrayList<ArrayList<String>> aircraftStrings = new ArrayList<>();

        for (Aircraft aircraft: aircrafts) {
            aircraftStrings.add(
                    new ArrayList<String>(Arrays.asList(
                            aircraft.getIata(),
                            aircraft.getName(),
                            aircraft.getIcao(),
                            Double.toString(aircraft.getRange())
                    )));
        }
        writeAll(aircraftStrings);
    }

    public ArrayList<Aircraft> readDatabaseAircrafts() {
        ResultSet results = databaseConnection.executeQuery("SELECT * FROM aircrafts");

        ArrayList<Aircraft> output = new ArrayList<>();

        try {
            while (results.next()) {
                output.add(new Aircraft(
                        results.getString("iata"),
                        results.getString("name"),
                        results.getString("icao"),
                        results.getDouble("range")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    public void writeDatabaseAircrafts(ArrayList<Aircraft> aircrafts) {
        databaseConnection.setAutoCommit(false);
        for (int i = 0; i < aircrafts.size(); i++) {
            try {
                PreparedStatement pStatement = databaseConnection.getFormattedPreparedStatement(
                        "INSERT INTO aircrafts (iata, name, icao, range)",
                        4
                );
                Aircraft aircraft = aircrafts.get(i);
                pStatement.setString(1, aircraft.getIata());
                pStatement.setString(2, aircraft.getName());
                pStatement.setString(3, aircraft.getIcao());
                pStatement.setDouble(4, aircraft.getRange());


                pStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        databaseConnection.commit();
        databaseConnection.setAutoCommit(true);
    }

    /**
     * Deletes the aircraft from the database
     * @param craft Aircraft to be deleted
     */
    public void deleteDatabaseAircraft(Aircraft craft) {
        String sql = String.format("DELETE FROM aircrafts WHERE name = '%s' AND iata = '%s' AND icao = '%s' and range = %f", craft.getName(), craft.getIata(), craft.getIcao(), craft.getRange());
        databaseConnection.executeStatement(sql);
    }

    /**
     * Uses the super class RWStream to write all aircrafts to a file.
     * The Aircraft object attributes have to be converted to strings.
     * @param aircrafts An ArrayList of Aircraft objects.
     */
    public void writeAircraftFile(ArrayList<Aircraft> aircrafts) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (Aircraft aircraft: aircrafts) {
            ArrayList<String> tempData = new ArrayList<String>();

            tempData.add(aircraft.getName());
            tempData.add(aircraft.getIata());
            tempData.add(aircraft.getIcao());
            String range = Double.toString(aircraft.getRange());
            tempData.add(range);

            data.add(tempData);
        }
        writeAll(data);
    }

}
