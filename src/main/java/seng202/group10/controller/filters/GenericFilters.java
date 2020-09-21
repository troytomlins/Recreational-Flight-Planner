package seng202.group10.controller.filters;


/**
 * TODO
 */
public class GenericFilters {
    protected FilterSender filterSender;

    public GenericFilters(String table) {
        filterSender = new FilterSender();
        filterSender.setTableName(table);
    }

    public void addFilter(String columnName, String pattern) {
        if (!pattern.equals("")) {
            filterSender.addFilter(columnName, pattern);
        }
    }
}
