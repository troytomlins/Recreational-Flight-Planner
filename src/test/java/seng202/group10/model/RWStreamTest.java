package seng202.group10.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Class for RWStream.
 */
public class RWStreamTest {

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
        ArrayList<ArrayList<String>> read_info = new ArrayList<>();
        ArrayList<ArrayList<String>> write_info = new ArrayList<>();
        ArrayList<ArrayList<String>> compareList = new ArrayList<>();
        String test1 = "This, Is, a, test, of, the, write, single, class";
        String test2 = "This, Is, a, test, of, the, write, all, class";
        ArrayList<String> splitList = new ArrayList<>(Arrays.asList(test2.split("[, ]+")));
        ArrayList<String> list = new ArrayList<>();
        list.add(test1);
        write_info.add(list);
        list.remove(test1);
        list.add(test2);
        write_info.add(list);
        RWStream rwstream = new RWStream("test2.csv");
        rwstream.writeAll(write_info);
        read_info = rwstream.read();
        compareList.add(splitList);
        assertEquals(compareList, read_info);

    }

}