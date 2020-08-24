package seng202.group10.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RWStreamTest {

    @Test
    public void testRead() {
        String test_string = "this, is, a, test";
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleList = new ArrayList<String>();
        singleList.add(test_string);
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
        ArrayList<ArrayList<String>> read_info = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> write_info = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> compareList = new ArrayList<ArrayList<String>>();
        String test1 = "This, Is, a, test, of, the, write, single, class";
        String test2 = "This, Is, a, test, of, the, write, all, class";
        ArrayList<String> list = new ArrayList<String>();
        list.add(test1);
        write_info.add(list);
        list.remove(test1);
        list.add(test2);
        write_info.add(list);
        RWStream rwstream = new RWStream("test2.csv");
        rwstream.writeAll(write_info);
        read_info = rwstream.read();
        compareList.add(list);
        assertEquals(compareList, read_info);

    }

}