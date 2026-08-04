package example.com.mock;

import example.com.enums.MockType;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MockLoader {
    private MockLoader() {
    }
    public static String getMockFile(MockType mockType) {
        String path = mockType.getPathToMockFile();

        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path)) {

            if (inputStream == null) {
                throw new IllegalStateException("Mock file not found: " + path);
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load mock file: " + path, e);
        }
    }
}
