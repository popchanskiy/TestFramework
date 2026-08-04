package example.com.extensions;

import com.codeborne.selenide.Selenide;
import example.com.annotations.AuthorizedUser;
import example.com.models.AuthorizedSession;
import example.com.utills.AuthorizedUserProvider;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class AuthorizedUserExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(AuthorizedUserExtension.class);
    private final String NAMESPACE_KEY = "authorization";

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        context.getStore(NAMESPACE).remove(NAMESPACE_KEY);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AuthorizedUser annotation = resolveAnnotation(context);
        if (annotation == null) {
            return;
        }
        AuthorizedSession authorizedSession = resolveSession(annotation);
        context.getStore(NAMESPACE).put(NAMESPACE_KEY, authorizedSession);
        Selenide.open(annotation.bootstrapPath());
        Selenide.executeJavaScript("window.localStorage.clear(); window.sessionStorage.clear();");
        Selenide.executeJavaScript(
                "window.localStorage.setItem(arguments[0], arguments[1]);",
                annotation.localStorageKey(),
                authorizedSession.token()
        );

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(AuthorizedSession.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        AuthorizedSession session = extensionContext.getStore(NAMESPACE).get(NAMESPACE_KEY, AuthorizedSession.class);
        if (session == null) {
            throw new ParameterResolutionException(
                    "AuthorizedSession is not available. Add @AuthorizedUser to the test method or class."
            );
        }
        return session;
    }

    private AuthorizedUser resolveAnnotation(ExtensionContext context) {
        AuthorizedUser annotationOnMethod = context.getRequiredTestMethod().getAnnotation(AuthorizedUser.class);
        if (annotationOnMethod != null) {
            return annotationOnMethod;
        }

        return context.getRequiredTestClass().getAnnotation(AuthorizedUser.class);
    }

    private AuthorizedSession resolveSession(AuthorizedUser annotation) {
        return switch (annotation.mode()) {
            case CREATE_USER_AND_LOGIN -> AuthorizedUserProvider.createAndLoginUser(annotation.role());
            case LOGIN_AS_ADMIN -> AuthorizedUserProvider.loginAdmin();
        };
    }


}
