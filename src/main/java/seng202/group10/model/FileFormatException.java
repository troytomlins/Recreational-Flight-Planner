package seng202.group10.model;

import java.util.ArrayList;

/**
 * Exception for files with only some lines that aren't of the correct format
 * @Author Tom Rizzi
 */
public class FileFormatException extends Exception{

    private String fileName;
    private ArrayList<Integer> lines;
    private String filePath;

    /**
     * Constructor
     * @param lines Lines of the file that are of incorrect format, starting at line 1
     * @param filePath Path of the file that has the incorrect lines
     */
    public FileFormatException(ArrayList<Integer> lines, String filePath) {
        super(String.format("%d lines in file '%s' of incorrect format.", lines.size(), filePath));
        this.filePath = filePath;
        this.lines = lines;
        this.fileName = createFileName(filePath);
    }

    public ArrayList<Integer> getLines() {
        return lines;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() { return fileName; }

    /**
     * Gets the file name out of a filepath string
     * @param file File path string
     * @return File name string
     */
    private String createFileName(String file) {
        // TODO Write Test
        char[] fileArray = file.toCharArray();
        StringBuilder fileName = new StringBuilder();
        for (int i = fileArray.length-1; i >= 0 ; i--){
            if (fileArray[i] == '/') {
                break;
            }
            fileName.insert(0, fileArray[i]);
        }
        return fileName.toString();
    }

}
