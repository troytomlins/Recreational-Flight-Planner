package seng202.group10.model;

import junit.framework.TestCase;


import java.util.ArrayList;

public class RWStreamTest extends TestCase {

    public void testRead() {
        String test_string = "this, is, a, test";
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> singlelist = new ArrayList<String>();
        singlelist.add(test_string);
        list.add(singlelist);
        RWStream rwstream = new RWStream("test.csv");
        data = rwstream.read();
        assertEquals(list, data);
    }

    public void testWriteSingle() {
        ArrayList<ArrayList<String>> read_info = new ArrayList<ArrayList<String>>();
        String test1 = "This, Is, a, test, of, the, write, single, class";
        ArrayList<String> list = new ArrayList<String>();
        list.add(test1);
        RWStream rwstream = new RWStream("test1.csv");
        rwstream.writeSingle(list);
        read_info = rwstream.read();
        assertEquals(read_info.get(0), list);


    }

    public void testWriteAll() {
        ArrayList<ArrayList<String>> read_info = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> write_info = new ArrayList<ArrayList<String>>();
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
        assertEquals(read_info, list);

    }
}