package seng202.group10.model;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for RWStream.
 */
public class RWStreamTest {

    @AfterClass
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void testRead() {
        String test_string = "this, is, a, test";
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> singleList = new ArrayList<>(Arrays.asList(test_string.split("[, ]+")));
        list.add(singleList);
        RWStream rwstream = new RWStream("test.csv");
        data = rwstream.read();
        assertEquals(list, data);
    }

    @Test
    public void testWriteSingle() {
        ArrayList<ArrayList<String>> read_info = new ArrayList<>();
        String test1 = "This, Is, a, test, of, the, write, single, class";
        ArrayList<String> compareList = new ArrayList<>(Arrays.asList(test1.split("[, ]+")));
        ArrayList<String> list = new ArrayList<>();
        list.add(test1);
        RWStream rwstream = new RWStream("test1.csv");
        rwstream.writeSingle(list);
        read_info = rwstream.read();
        assertEquals(compareList, read_info.get(0));
    }

    @Test
    public void testWriteAll() {
        String filePath = "testWriteAll.txt";
        File testFile = new File(filePath);
        try {
            // Setup
            ArrayList<ArrayList<String>> testArray = new ArrayList<>();
            testArray.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
            testArray.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
            String expected = "1,2,3\n4,5,6";

            // Write to file
            RWStream stream = new RWStream("", filePath);
            stream.writeAll(testArray);

            // Check file
            String readString = Files.readString(Path.of(filePath));
            assertEquals(expected, readString);

        } catch (IOException ignore) {
        } finally {
            testFile.delete();
        }




    }

}