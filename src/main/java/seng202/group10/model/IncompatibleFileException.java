package seng202.group10.model;

public class IncompatibleFileException extends Exception {

    public IncompatibleFileException() {
        super("File selected not compatible.");
    }
}
