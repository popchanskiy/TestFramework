package example.com.annotations;

import example.com.enums.AuthorizedMode;
import example.com.enums.Role;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AuthorizedUser {
    AuthorizedMode mode() default AuthorizedMode.CREATE_USER_AND_LOGIN;
    Role role() default Role.USER;
    String localStorageKey() default "authToken";
    String bootstrapPath() default "/";
}
