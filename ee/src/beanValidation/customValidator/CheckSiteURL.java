package beanValidation.customValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

@Constraint(validatedBy = {CheckSiteLogic.class})
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSiteURL {
    String message() default"Wrong URL";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};

    int port() default -1;
    String host() default "";
    String protocol() default "";
}
