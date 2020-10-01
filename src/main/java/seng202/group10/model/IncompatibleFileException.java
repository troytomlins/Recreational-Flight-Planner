package seng202.group10.model;

/**
 * Exception for files that are incompatible.
 */
public class IncompatibleFileException extends Exception {

    /**
     * Constructor for IncompatibleFileException.
     * Sends "File selected not compatible." to Exception Class.
     */
    public IncompatibleFileException() {
        super("File selected not compatible.");
    }
}
