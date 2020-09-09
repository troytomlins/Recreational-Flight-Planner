package seng202.group10.model;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Arrays;


/**
 * @author Mitchell
 * @author Niko
 */
public class RWStream {
    private Scanner fileReader;
    private FileWriter fileWriter;
    private String inFilename;
    private String outFilename;

    public RWStream(String filename) {
        inFilename = filename;
        outFilename = filename;
        makeFile();
    }

    public RWStream(String inFilename, String outFilename) {
        this.inFilename = inFilename;
        this.outFilename = outFilename;
        makeFile();
    }

    private void makeFile() {
        try {
            File file = new File(outFilename);
            file.createNewFile();
        } catch(IOException error) {

        }
    }

    public ArrayList<ArrayList<String>> read() {
        try {
            fileReader = new Scanner(new File(inFilename));
        } catch (FileNotFoundException error) {

        }
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            ArrayList<String> lineList = new ArrayList<String>(Arrays.asList(line.split("[, ]+")));
            lines.add(lineList);
        }

        fileReader.close();
        return lines;
    }

    public String getInFilename() {
        return inFilename;
    }

    public void writeSingle(ArrayList<String> data) {
        try {
            fileWriter = new FileWriter(outFilename);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < data.size() - 1; i++) {
                builder.append(data.get(i));
                builder.append(',');
            }
            builder.append(data.get(data.size() - 1));
            builder.append('\n');

            fileWriter.write(builder.toString());

            fileWriter.close();
        } catch (IOException error) {

        }
    }

    public void writeAll(ArrayList<ArrayList<String>> data) {
        File file = new File(outFilename);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch(IOException error) {

        }
        for (ArrayList<String> dataLine: data) {
            writeSingle(dataLine);
        }
    }
}
