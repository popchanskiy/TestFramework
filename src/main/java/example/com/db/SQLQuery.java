package example.com.db;

import java.util.List;

public class SQLQuery {
    private final String sql;
    private final List<Object> params;

    public SQLQuery(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public static SqlQueryBuilder builder() {
        return new SqlQueryBuilder();
    }
}
