package seng202.group10.controller.filters;


/**
 * TODO
 */
public class Filter {

    private String columnName;
    private String pattern;

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
