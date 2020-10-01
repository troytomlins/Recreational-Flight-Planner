package seng202.group10.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DatabaseConnectionTest {

    DatabaseConnection database;

    @Before
    public void setUp() throws Exception {
        database = DatabaseConnection.getInstance();
        File file = new File("database.db");
        assertTrue(file.exists());
    }

    @AfterEach
    public void tearDown() throws Exception {
        database.disconnect();
        File file = new File("database.db");
        file.delete();
    }

    @Test
    public void databaseSchemaCreationTest() throws SQLException {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("CREATE TABLE airlines (id integer PRIMARY KEY AUTOINCREMENT,name varchar,alias varchar,iata char(3),icao char(4) UNIQUE,callsign varchar,country varchar)");
        expected.add("CREATE TABLE airports (id integer PRIMARY KEY AUTOINCREMENT,name varchar,city varchar,country varchar,iata char(3) UNIQUE,icao char(4) UNIQUE,latitude double,longitude double,altitude float,timezone float,dstType char(1),tzDatabase varchar)");
        expected.add("CREATE TABLE aircrafts (id integer PRIMARY KEY AUTOINCREMENT,name varchar,iata char(3),icao char(4),range double)");
        expected.add("CREATE TABLE routes (id integer PRIMARY KEY AUTOINCREMENT,airlineCode varchar,sourceAirportCode varchar,destinationAirportCode varchar,stops int)");
        expected.add("CREATE TRIGGER airlineTriggerIata    AFTER INSERT ON airlines    WHEN (NEW.iata = '' OR NEW.iata = '\\N')BEGIN    UPDATE airlines SET iata = null WHERE id = NEW.id;END");
        expected.add("CREATE TRIGGER airlineTriggerIcao    AFTER INSERT ON airlines    WHEN (NEW.icao = '' OR NEW.icao = '\\N')BEGIN    UPDATE airlines SET icao = null WHERE id = NEW.id;END");
        expected.add("CREATE TRIGGER airportTriggerIata    AFTER INSERT ON airports    WHEN (NEW.iata = '' OR NEW.iata = '\\N')BEGIN    UPDATE airports SET iata = null WHERE id = NEW.id;END");
        expected.add("CREATE TRIGGER airportTriggerIcao    AFTER INSERT ON airports    WHEN (NEW.icao = '' OR NEW.icao = '\\N')BEGIN    UPDATE airports SET icao = null WHERE id = NEW.id;END");

        String sqlQuery = "SELECT sql\n" +
                "FROM sqlite_master\n" +
                "WHERE name in ('aircrafts', 'airlines', 'airports', 'routes', 'airlineTriggerIata', 'airlineTriggerIcao', 'airportTriggerIata', 'airportTriggerIcao')";

        ResultSet rs = database.executeQuery(sqlQuery);
        ArrayList<String> actual = new ArrayList<String>();

        while (rs.next()) {
            actual.add(rs.getString("sql"));
        }

        assertEquals(expected, actual);
    }
}