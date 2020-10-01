package seng202.group10.model;


import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AirportRWTest {

    private final String goodFileString = "src/test/resources/seng202.group10/model/airportsGood.dat";
    private final String badFileString = "src/test/resources/seng202.group10/model/airportsBad.dat";
    private final String corruptFileString = "src/test/resources/seng202.group10/model/airportsCorrupt.dat";

    @AfterClass
    public static void tearDown() {
        RWStream stream = new RWStream("a");
        stream.closeDb();
    }

    @Test
    public void readFileReturnsCorrectArrayGoodFile() throws FileFormatException, IncompatibleFileException {
        ArrayList<Airport> correctArray = new ArrayList<>();
        correctArray.add(new Airport("Goroka", "Goroka", "Papua New Guinea", "GKA", "AYGA", -6.081689, 145.391881, 5282, 10, "U", "Pacific/Port_Moresby"));
        correctArray.add(new Airport("Madang","Madang","Papua New Guinea","MAG","AYMD",-5.207083,145.7887,20,10,"U","Pacific/Port_Moresby"));
        correctArray.add(new Airport("Mount Hagen","Mount Hagen","Papua New Guinea","HGU","AYMH",-5.826789,144.295861,5388,10,"U","Pacific/Port_Moresby"));

        AirportRW stream = new AirportRW(goodFileString);
        assertEquals(stream.readAirports().size(), correctArray.size());
    }

    @Test
    public void readFileThrowsErrorBadFile() {
        AirportRW stream = new AirportRW(badFileString);
        assertThrows(FileFormatException.class, stream::readAirports);
    }

    @Test
    public void readFileIgnoreLinesBadFile() throws FileFormatException, IncompatibleFileException {
        AirportRW stream = new AirportRW(badFileString);
        ArrayList<Integer> ignoreLines = new ArrayList<>();
        ignoreLines.add(2);
        ignoreLines.add(3);
        assertEquals(stream.readAirports(ignoreLines).size(), 1);
    }

    @Test
    public void readFileThrowsErrorCorruptFile() {
        AirportRW stream = new AirportRW(corruptFileString);
        assertThrows(IncompatibleFileException.class, stream::readAirports);
    }

}
