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
            "id int PRIMARY KEY," +
            "name varchar," +
            "alias varchar," +
            "iata varchar," +
            "icao varchar," +
            "callsign varchar," +
            "country varchar" +
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

    public void connect() {

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

    public void createTables() {
        try {
            Statement statement = conn.createStatement();
            statement.execute(airlineTable);
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

    public ResultSet executeStatement(String sqlStatement) {
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
