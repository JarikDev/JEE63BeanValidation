package beanValidation.ValidationLesson;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.executable.ExecutableValidator;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

@WebServlet("/validationLesson")
public class ValidationLesson extends HttpServlet {
    @Inject
    Person person;
//    @Inject
//    Validation validation;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        person.name="max";
        person.age=15;

        ValidatorFactory validatorFactory=Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();

        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        if(validate.size()>0){
            System.out.println("Some error occured");
        }
        for (ConstraintViolation<Person> violation:validate) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        Set<ConstraintViolation<Person>> name = validator.validateProperty(person, "name");
        if (name.size()>0){
            System.out.println("name is wrong");
        }

        Set<ConstraintViolation<Person>> constraintViolations = validator.validateValue(Person.class, "name", "anton");
        if (constraintViolations.size()>0){
            System.out.println("anton is wrong");
        }

        try {
            Method setName = Person.class.getMethod("setName", String.class);
            ExecutableValidator executableValidator = validator.forExecutables();
            Set<ConstraintViolation<Class<Person>>> constraintViolations1 = executableValidator.validateParameters(Person.class, setName, new Object[]{null});
            if(constraintViolations1.size()>0){
                System.out.println("parameter was wrong");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        validatorFactory.close();
    }
}

class Person {
    @Pattern(regexp = "[A-Z][a-z]*")
    String name;
    @Min(18)
    int age;
//     @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}