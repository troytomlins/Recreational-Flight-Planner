package seng202.group10.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class RWStreamTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RWStreamTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RWStreamTest.class);
    }

    public void testRead() {
        RWStream rw = new RWStream("testfile.csv");
        try {
            FileWriter writer = new FileWriter("testfile.csv");
            writer.write("test1,test2,test3");

            ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
            expected.add(new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));

            //assertArrayEquals(expected.get(0), rw.read().get(0));
        } catch (IOException error) {

        }
    }

    public void testWriteSingle() {
        RWStream rw = new RWStream("testfile.csv");
        rw.writeSingle(new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
        File file = new File("testfile.csv");

        try {
            Scanner scanner = new Scanner(file);
            assertEquals("test1,test2,test3", scanner.nextLine());
        } catch (FileNotFoundException error) {

        }



        file.delete();
    }

    public void testWriteAll() {
    }
}