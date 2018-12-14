package beanValidation.classValidation;

import javafx.scene.PerspectiveCamera;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
@WebServlet("/classValidation")
public class ClassValidation extends HttpServlet{
    @Inject
    Validator validator;
@Inject
    Person person;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        person.setBirtDate(LocalDate.of(3977,01,01));
        person.setFirstSexDate(LocalDate.of(2117,01,01));
        Set<ConstraintViolation<Person>> dates = validator.validate(person);
        for (ConstraintViolation violation : dates) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

    }
}
@ChronDates
class Person{
    LocalDate birtDate;
    LocalDate firstSexDate;

    public LocalDate getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(LocalDate birtDate) {
        this.birtDate = birtDate;
    }

    public LocalDate getFirstSexDate() {
        return firstSexDate;
    }

    public void setFirstSexDate(LocalDate firstSexDate) {
        this.firstSexDate = firstSexDate;
    }
}

class   CheckChronologicalDates implements ConstraintValidator<ChronDates,Person>{
    @Override
    public void initialize(ChronDates constraintAnnotation) {

    }

    @Override
    public boolean isValid(Person person, ConstraintValidatorContext constraintValidatorContext) {
        return person.birtDate.isBefore(person.firstSexDate);
    }
}