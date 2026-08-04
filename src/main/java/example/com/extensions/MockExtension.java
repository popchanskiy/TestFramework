package example.com.extensions;

import example.com.annotations.WithMock;
import example.com.config.AppConfig;
import example.com.enums.MockType;
import example.com.mock.MockLoader;
import example.com.mock.MockManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MockExtension implements BeforeEachCallback, AfterEachCallback {
    private MockManager mockManager;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        mockManager = new MockManager(AppConfig.get("mock_remote"));
        mockManager.resetAllMocks();
        WithMock mock = resolveMock(context);
        MockType mockType = mock.value();
        String mockConfig = MockLoader.getMockFile(mockType);
        mockManager.registerNewStub(mockConfig);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (mockManager != null) {
            mockManager.resetAllMocks();
        }
    }

    private WithMock resolveMock(ExtensionContext context) {
        WithMock annotation = context.getRequiredTestMethod().getAnnotation(WithMock.class);
        if (annotation != null) {
            return annotation;
        }
        return context.getRequiredTestClass().getAnnotation(WithMock.class);
    }
}


