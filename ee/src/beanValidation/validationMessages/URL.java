package beanValidation.validationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {URLValidation.class})
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RUNTIME)
public @interface URL {
    String message() default "{javax.validation.constraints.url.port.message}";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};

    int port() default -1;
    String host() default "";
    String protocol() default "";
    String url() default "";
}
