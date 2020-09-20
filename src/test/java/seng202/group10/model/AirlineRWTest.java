package seng202.group10.model;


import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class AirlineRWTest {
    private final String goodFileString = "src/test/resources/seng202.group10/model/airlinesGood.dat";
    private final String badFileString = "src/test/resources/seng202.group10/model/airlinesBad.dat";
    private final String corruptFileString = "src/test/resources/seng202.group10/model/airlinesCorrupt.dat";

    @Test
    public void readFileReturnsCorrectArrayGoodFile() throws FileFormatException, IncompatibleFileException {
        ArrayList<Airline> correctArray = new ArrayList<>();
        correctArray.add(new Airline("135 Airways","","","GNL","GENERAL","United States"));
        correctArray.add(new Airline("1Time Airline","","1T","RNX","NEXTIME","South Africa"));
        correctArray.add(new Airline("2 Sqn No 1 Elementary Flying Training School","","","WYT","","United Kingdom"));

        AirlineRW stream = new AirlineRW(goodFileString);
        assertEquals(stream.readAirlines().size(), correctArray.size());
    }

    @Test
    public void readFileThrowsErrorBadFile() {
        AirlineRW stream = new AirlineRW(badFileString);
        assertThrows(FileFormatException.class, stream::readAirlines);
    }

    @Test
    public void readFileIgnoreLinesBadFile() throws FileFormatException, IncompatibleFileException {
        AirlineRW stream = new AirlineRW(badFileString);
        ArrayList<Integer> ignoreLines = new ArrayList<>();
        ignoreLines.add(2);
        ignoreLines.add(3);
        assertEquals(stream.readAirlines(ignoreLines).size(), 1);
    }

    @Test
    public void readFileThrowsErrorCorruptFile() {
        AirlineRW stream = new AirlineRW(corruptFileString);
        System.out.println();
        assertThrows(IncompatibleFileException.class, stream::readAirlines);
    }
}
