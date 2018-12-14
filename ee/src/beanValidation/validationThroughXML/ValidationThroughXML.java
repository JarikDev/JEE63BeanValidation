package beanValidation.validationThroughXML;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

@WebServlet("/validationThroughXML")
public class ValidationThroughXML extends HttpServlet {
    @Inject
    Validator validator;
    @Inject
    Person person;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ConstraintViolation<Person>> name = validator.validate(person);
        for (ConstraintViolation violation : name) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }
    }
}

class Person {
    String name;
    int age;
}