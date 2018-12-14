package beanValidation.classValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {CheckChronologicalDates.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface ChronDates {
    String message() default"Invalid Date";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
