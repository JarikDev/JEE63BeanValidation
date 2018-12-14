package beanValidation.validationGroups;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

@WebServlet("/validationGroups")
public class ValidationGroups extends HttpServlet {
    @Inject
    Validator validator;
    @Inject
    Person person;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        person.s1="";
        person.s2="";
        person.s3=null;
        person.s4=null;
        Set<ConstraintViolation<Person>> name = validator.validate (person, Group3.class);
        for (ConstraintViolation violation : name) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }
    }
}
interface Group1{}
interface Group2{}
interface Group3{}
interface Group4{}
class Person {
    @NotNull(groups = Group1.class)
    String s1;
    @NotNull(groups = {Group1.class,Group2.class,Default.class})
    String s2;
    @NotNull(groups = Group3.class )
    String s3;
    @NotNull(groups = Default.class)//@NotNull
    String s4;
}