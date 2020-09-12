package seng202.group10.model;

import java.sql.*;


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
            "id integer PRIMARY KEY AUTOINCREMENT," +
            "name varchar," +
            "city varchar," +
            "country varchar," +
            "iata char(3)," +
            "icao char(4)," +
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
            "id integer PRIMARY KEY AUTOINCREMENT," +
            "airline int," +
            "airlineCode varchar," +
            "sourceAirport int," +
            "sourceAirportCode varchar," +
            "destinationAirport int," +
            "destinationAirportCode varchar," +
            "stops int," +
            "FOREIGN KEY (airline) references airlines(id) ON DELETE SET NULL," +
            "FOREIGN KEY (sourceAirport) references airports(id) ON DELETE SET NULL," +
            "FOREIGN KEY (destinationAirport) references airports(id) ON DELETE SET NULL" +
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
            System.out.println(sqlStatement);
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

    public PreparedStatement getFormattedPreparedStatement(String firstline, int nParameters) throws SQLException{
        String statement = firstline + " VALUES \n";

        statement += "(?";
        for (int j = 1; j < nParameters; j++) {
            statement += ", ?";
        }
        statement += ")";

        return conn.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
    }

    public PreparedStatement getPreparedStatement(String sqlStatement) throws SQLException {
        return conn.prepareStatement(sqlStatement);
    }

    public void setAutoCommit(Boolean b) {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        DatabaseConnection database = DatabaseConnection.getInstance();
    }
}
