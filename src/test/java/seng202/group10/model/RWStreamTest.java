package seng202.group10.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileNotFoundException;
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