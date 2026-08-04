package example.com.db;

import java.util.List;
import java.util.Map;

public class QueryResult {
    private final List<Map<String, Object>> rows;

    public QueryResult(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public <T> T extractAs(Class<T> clazz) {
        return ResultMapper.mapOne(rows, clazz);
    }

    public <T> List<T> extractListAs(Class<T> clazz) {
        return ResultMapper.mapMany(rows, clazz);
    }

    public List<Map<String, Object>> raw() {
        return rows;
    }
}
