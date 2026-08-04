package example.com.db;

import example.com.enums.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SqlQueryBuilder {
    private Operation operation;
    private final List<String> columns = new ArrayList<>();
    private String table;
    private final List<String> where = new ArrayList<>();
    private final List<Object> params = new ArrayList<>();
    private boolean allColumnSelected;


    public SqlQueryBuilder operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public SqlQueryBuilder column(String column) {
        this.columns.add(column);
        return this;
    }

    public SqlQueryBuilder all() {
        this.columns.add("*");
        this.allColumnSelected = true;
        return this;
    }

    public SqlQueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    public SqlQueryBuilder whereEquals(String column, Object value) {
        this.where.add(column + " = ?");
        this.params.add(value);
        return this;
    }

    public SQLQuery build() {
        if (operation == null) {
            throw new IllegalStateException("Condition is required");
        }

        if (table == null || table.isBlank() || allColumnSelected != true) {
            throw new IllegalStateException("Table is required");
        }

        if (columns.isEmpty()) {
            throw new IllegalStateException("At least one column is required");
        }

        if (operation != Operation.SELECT) {
            throw new IllegalStateException("Only SELECT is supported");
        }

        StringJoiner sql = new StringJoiner(" ");
        sql.add(operation.getOperation());
        if (!allColumnSelected) {
            sql.add(String.join(", ", columns));
        }
        sql.add("*");
        sql.add("from");

        sql.add(table);


        if (!where.isEmpty()) {
            sql.add("where");
            sql.add(String.join(" and ", where));
        }

        return new SQLQuery(sql.toString(), List.copyOf(params));
    }
}

