package example.com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbClient {
    private final DbConfig dbConfig;
    private final SQLQuery sqlQuery;
    public DbClient(DbConfig dbConfig, SQLQuery sqlQuery) {
        this.dbConfig = dbConfig;
        this.sqlQuery = sqlQuery;
    }

    public QueryResult execQuery() {
        try (Connection connection = DriverManager.getConnection(
                dbConfig.url(),
                dbConfig.username(),
                dbConfig.password()
        );
             PreparedStatement statement = connection.prepareStatement(sqlQuery.getSql())) {

            bindParams(statement, sqlQuery.getParams());

            try (ResultSet rs = statement.executeQuery()) {
                List<Map<String, Object>> rows = readRows(rs);
                return new QueryResult(rows);
            }
        } catch (Exception e) {
            throw new RuntimeException("DB query failed: " + sqlQuery.getSql(), e);
        }
    }

    private void bindParams(PreparedStatement statement, List<Object> params) throws Exception {
        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }
    }

    private List<Map<String, Object>> readRows(ResultSet rs) throws Exception {
        List<Map<String, Object>> rows = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()) {
            Map<String, Object> row = new LinkedHashMap<>();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnLabel(i);
                Object value = rs.getObject(i);
                row.put(columnName, value);
            }

            rows.add(row);
        }

        return rows;
    }
}
