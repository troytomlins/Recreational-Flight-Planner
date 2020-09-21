package seng202.group10.controller.filters;

import seng202.group10.model.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * TODO
 * @author Mitchell
 */
public class FilterSender {
    // TODO method docs and split up methods

    private String tableName;
    private ArrayList<Filter> filters = new ArrayList<Filter>();

    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void addFilter(String columnName, String pattern) {
        if (!columnName.equals("")) {
            filters.add(new Filter(columnName, pattern));
        }
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

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

        filters = new ArrayList<Filter>();
        return results;
    }
}
