package seng202.group10.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RWStreamTest {

    @Test
    public void testRead() {
        String test_string = "this, is, a, test";
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleList = new ArrayList<String>(Arrays.asList(test_string.split("[, ]+")));
        list.add(singleList);
        RWStream rwstream = new RWStream("test.csv");
        data = rwstream.read();
        assertEquals(list, data);
    }

    @Test
    public void testWriteSingle() {
        ArrayList<ArrayList<String>> read_info = new ArrayList<ArrayList<String>>();
        String test1 = "This, Is, a, test, of, the, write, single, class";
        ArrayList<String> compareList = new ArrayList<String>(Arrays.asList(test1.split("[, ]+")));
        ArrayList<String> list = new ArrayList<String>();
        list.add(test1);
        RWStream rwstream = new RWStream("test1.csv");
        rwstream.writeSingle(list);
        read_info = rwstream.read();
        assertEquals(compareList, read_info.get(0));
    }

    @Test
    public void testWriteAll() {
        ArrayList<ArrayList<String>> writeInfo = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>> compareList = new ArrayList<ArrayList<String>>();

        String line1 = "This, Is, a, test, of, the, write, single, class";
        String line2 = "This, Is, a, test, of, the, write, all, class";

        ArrayList<String> arr1 = new ArrayList<String>(Arrays.asList(line1.split("[, ]+")));
        ArrayList<String> arr2 = new ArrayList<String>(Arrays.asList(line2.split("[, ]+")));

        ArrayList<ArrayList<String>> expectedArr = new ArrayList<>();

        writeInfo.add(arr1);
        writeInfo.add(arr2);
        expectedArr.add(arr1);
        expectedArr.add(arr2);

        RWStream rwstream = new RWStream("test2.csv");

        rwstream.writeAll(writeInfo);
        ArrayList<ArrayList<String>> readInfo = rwstream.read();
        assertEquals(expectedArr, readInfo);

    }

}