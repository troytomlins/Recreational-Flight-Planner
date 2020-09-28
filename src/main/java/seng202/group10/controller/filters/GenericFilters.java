package seng202.group10.controller.filters;

/**
 * Class for setting Generic Filters.
 */
public class GenericFilters {

    protected FilterSender filterSender;

    /**
     * Constructor for GenericFilters.
     * Creates a new instance of FilterSender and sets the table name to inputted string.
     * @param table Table name
     */
    public GenericFilters(String table) {
        filterSender = new FilterSender();
        filterSender.setTableName(table);
    }

    /**
     * Adds a filter to filterSender.
     * @param columnName Column name
     * @param pattern Text to match
     */
    public void addFilter(String columnName, String pattern) {
        if (!pattern.equals("")) {
            filterSender.addFilter(columnName, pattern);
        }
    }
}
