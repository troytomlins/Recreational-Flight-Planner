package seng202.group10.model;
import javafx.scene.control.Alert;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Arrays;

import com.google.common.base.Joiner;


/**
 * Super class for file readers
 * Reads in files, writes to files
 * Connects to database
 * @author Mitchell Freeman
 * @author Niko Tainui
 * @author Johnny Howe
 */
public class RWStream {
    private Scanner fileReader;
    private FileWriter fileWriter;
    private String inFilename;
    private String outFilename;
    public DatabaseConnection databaseConnection;

    /**
     * Constructor for RWStream, sets in and out file to inputted filename.
     * Creates the out file, gets the database instance
     * @param filename - file to import
     */
    public RWStream(String filename) {
        inFilename = filename;
        outFilename = filename;
        makeFile();
        getDatabase();
    }

    /**
     * Constructor for RWStream, sets in and out to separate files.
     * Creates the out file, gets the database instance.
     * @param inFilename in file
     * @param outFilename out file
     */
    public RWStream(String inFilename, String outFilename) {
        this.inFilename = inFilename;
        this.outFilename = outFilename;
        makeFile();
        getDatabase();
    }

    /**
     * Closes the database connection
     */
    public void closeDb() {
        databaseConnection.disconnect();
    }

    /**
     * Creates file at outFilename
     */
    private void makeFile() {
        try {
            File file = new File(outFilename);
            file.createNewFile();
        } catch(IOException error) {
            displayError("Unable to make file");
        }
    }

    /**
     * Sets the attribute databaseConnection to an instance of the database
     */
    private void getDatabase() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    /**
     * Contents of file at inFilename
     * Each line is split by commas and put into an arraylist
     * Each line arraylist is then put into another arraylist which is returned
     * @return ArrayList<ArrayList<String>> thing explained before
     */
    public ArrayList<ArrayList<String>> read() {
        try {
            fileReader = new Scanner(new File(inFilename));
        } catch (FileNotFoundException error) {
            displayError("File not found");
        }
        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split("[, ]+")));
            lines.add(lineList);
        }
        fileReader.close();
        return lines;
    }

    public String getInFilename() {
        return inFilename;
    }

    /**
     * Write a single line to the file outFilename (and newline char).
     * line is all values in data separated by commas.
     * @param data list of data to write on the line
     */
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
            displayError("Unable to write to file");
        }
    }

    /**
     * Writes all the lines specified in data to the file at outFilename
     * each entry of data is another ArrayList of strings, that list is added to the file according
     *  to writeSingle.
     * @param data list of line lists
     */
    public void writeAll(ArrayList<ArrayList<String>> data) {

        File file = new File(outFilename);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch(IOException error) {

        }
        try {
            fileWriter = new FileWriter(outFilename);
            StringBuilder builder;
            StringBuilder fileContent = new StringBuilder();
            int count = 0;
            for (ArrayList<String> line: data) {
                builder = new StringBuilder();
                for (int i = 0; i < line.size(); i++) {
                    builder.append(line.get(i));
                    if (i < line.size() -1) {
                        builder.append(",");
                    }
                }
                if (count < data.size() - 1) {
                    builder.append('\n');
                }
                count++;
                fileContent.append(builder.toString());
            }
            fileWriter.write(fileContent.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Sets the out file path
     * @param filepath
     */
    public void setOutFileName(String filepath) {
        outFilename = filepath;
    }

    /**
     * Displays Error message.
     * @param message Message to display
     */
    public void displayError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }
}
