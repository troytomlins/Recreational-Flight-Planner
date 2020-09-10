package seng202.group10.model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public final class DatabaseConnection {

    private static DatabaseConnection INSTANCE;

    private Connection conn = null;

    private final String airlineTable = "CREATE TABLE IF NOT EXISTS airlines (" +
            "id integer PRIMARY KEY AUTOINCREMENT," +
            "name varchar," +
            "alias varchar," +
            "iata char(3)," +
            "icao char(4)," +
            "callsign varchar," +
            "country varchar" +
            ")";

    private final String airportTable = "CREATE TABLE IF NOT EXISTS airports (" +
            "id int PRIMARY KEY," +
            "name varchar," +
            "city varchar," +
            "country varchar," +
            "iata char(2)," +
            "icao char(3)," +
            "latitude double," +
            "longitude double," +
            "altitude float," +
            "timezone float," +
            "dstType char(1)," +
            "tzDatabase varchar" +
            ")";

    private final String aircraftTable = "CREATE TABLE IF NOT EXISTS aircrafts (" +
            "id int PRIMARY KEY," +
            "name varchar," +
            "iata char(3)," +
            "icao char(4)," +
            "fuelRate double" +
            ")";

    private final String routeTable = "CREATE TABLE IF NOT EXISTS routes (" +
            "id int PRIMARY KEY," +
            "airline int," +
            "sourceAirport int," +
            "destinationAirport int," +
            "stops int," +
            "FOREIGN KEY (airline) references airlines(id)," +
            "FOREIGN KEY (sourceAirport) references airports(id)," +
            "FOREIGN KEY (destinationAirport) references airports(id)" +
            ")";

    private DatabaseConnection() {
        connect();
        createTables();
    }

    public static DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }

    private void connect() {

        try {
            // db parameters
            //Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTables() {
        try {
            Statement statement = conn.createStatement();
            statement.execute(airlineTable);
            statement.execute(airportTable);
            statement.execute(aircraftTable);
            statement.execute(routeTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void executeStatement(String sqlStatement) {
        try {
            Statement statement = conn.createStatement();
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet executeQuery(String sqlStatement) {
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sqlStatement);
            return results;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static void main(String[] args) {
        DatabaseConnection database = DatabaseConnection.getInstance();
    }
}
