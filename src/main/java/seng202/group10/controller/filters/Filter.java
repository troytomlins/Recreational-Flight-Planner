package seng202.group10.controller.filters;

/**
 * Class for defining filters.
 */
public class Filter {

    private String columnName;
    private String pattern;

    /**
     * Constructor for Filter Class.
     * Sets name of the column for filter to apply to and the pattern to match.
     * @param columnName Name of column
     * @param pattern Pattern of text to match
     */
    public Filter(String columnName, String pattern) {
        this.columnName = columnName;
        this.pattern = pattern;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getPattern() {
        return pattern;
    }
}
