package seng202.group10.model;




import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Test class for AirlineRW.
 */
public class AirlineRWTest {
    private final String goodFileString = "src/test/resources/seng202.group10/model/airlinesGood.dat";
    private final String badFileString = "src/test/resources/seng202.group10/model/airlinesBad.dat";
    private final String corruptFileString = "src/test/resources/seng202.group10/model/airlinesCorrupt.dat";
    static AirlineRW stream;


    @Test
    public void readFileReturnsCorrectArrayGoodFile() throws FileFormatException, IncompatibleFileException {
        ArrayList<Airline> correctArray = new ArrayList<>();
        correctArray.add(new Airline("135 Airways","","","GNL","GENERAL","United States"));
        correctArray.add(new Airline("1Time Airline","","1T","RNX","NEXTIME","South Africa"));
        correctArray.add(new Airline("2 Sqn No 1 Elementary Flying Training School","","","WYT","","United Kingdom"));

        stream = new AirlineRW(goodFileString);
        assertEquals(stream.readAirlines().size(), correctArray.size());
    }

    @Test
    public void readFileThrowsErrorBadFile() {
        stream = new AirlineRW(badFileString);
        assertThrows(FileFormatException.class, stream::readAirlines);
    }

    @Test
    public void readFileIgnoreLinesBadFile() throws FileFormatException, IncompatibleFileException {
        stream = new AirlineRW(badFileString);
        ArrayList<Integer> ignoreLines = new ArrayList<>();
        ignoreLines.add(2);
        ignoreLines.add(3);
        assertEquals(stream.readAirlines(ignoreLines).size(), 1);
    }

    @Test
    public void readFileThrowsErrorCorruptFile() {
        stream = new AirlineRW(corruptFileString);
        assertThrows(IncompatibleFileException.class, stream::readAirlines);
    }

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
        new File("database.db").delete();
    }
}
