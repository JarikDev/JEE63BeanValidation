package beanValidation.customValidator;

import com.sun.prism.shader.Solid_RadialGradient_REFLECT_AlphaTest_Loader;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.*;

@NotNull
@Size(min=7)
@Pattern(regexp ="[A-Za-z0-9]*@[A-Za-z0-9]*\\.com")
@Constraint(validatedBy = {})
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEmail   {
    String message() default"Email address doesn't look good";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
