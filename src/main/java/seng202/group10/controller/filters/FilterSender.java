package seng202.group10.controller.filters;

import seng202.group10.model.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mitchell
 */
public class FilterSender {

    private String tableName;
    private ArrayList<Filter> filters;

    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void addFilter(String columnName, String pattern) {
        filters.add(new Filter(columnName, pattern));
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ResultSet applyFilter() {
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append(String.format("SELECT * FROM %s WHERE ", tableName));

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

        return results;
    }
}
