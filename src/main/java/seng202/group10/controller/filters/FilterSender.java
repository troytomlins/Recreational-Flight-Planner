package seng202.group10.controller.filters;

import seng202.group10.model.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Apply Filters on the dataViewer.
 * @author Mitchell
 */
public class FilterSender {

    private String tableName;
    private ArrayList<Filter> filters = new ArrayList<>();

    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    /**
     * Adds a filter to ArrayList filters.
     * @param columnName Name of Column
     * @param pattern Pattern of text to match
     */
    public void addFilter(String columnName, String pattern) {
        if (!columnName.equals("")) {
            filters.add(new Filter(columnName, pattern));
        }
    }

    /**
     * Sets the table name for filters.
     * @param tableName Name of table
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Applies filters to data in database.
     * @return ResultSet of data
     */
    public ResultSet applyFilter() {
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append(String.format("SELECT * FROM %s ", tableName));

        if (filters.size() > 0) {
            statementBuilder.append("WHERE ");
        }

        for (int i = 0; i < filters.size(); i++) {
            statementBuilder.append(String.format("(%s LIKE ?)", filters.get(i).getColumnName()));
            if (i < filters.size() - 1) {
                statementBuilder.append(" AND ");
            }
        }

        ResultSet results = null;

        try {
            PreparedStatement statement = databaseConnection.getPreparedStatement(statementBuilder.toString());

            for (int i = 0; i < filters.size(); i++) {
                //TODO: Escape '%' and '_' characters from the pattern
                statement.setString(i + 1, "%" + filters.get(i).getPattern() + "%");
            }

            results = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        filters = new ArrayList<>();
        return results;
    }
}
