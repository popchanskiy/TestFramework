package example.com.db;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class ResultMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T mapOne(List<Map<String, Object>> rows, Class<T> clazz) {
        if (rows.isEmpty()) {
            throw new IllegalStateException("Query returned no rows");
        }

        if (rows.size() > 1) {
            throw new IllegalStateException("Query returned more than one row");
        }

        return objectMapper.convertValue(rows.get(0), clazz);
    }

    public static <T> List<T> mapMany(List<Map<String, Object>> rows, Class<T> clazz) {
        return rows.stream()
                .map(row -> objectMapper.convertValue(row, clazz))
                .toList();
    }
}
