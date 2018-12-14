package beanValidation.inheritanceAndValidation;

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
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@WebServlet("/inheritanceAndValidation")
public class InheritanceAndValidation extends HttpServlet {
    @Inject
    Validator validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<ConstraintViolation<Child>> name = validator.validateValue(Child.class, "name", null);
        for (ConstraintViolation violation : name) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

    }
}

class Person {
    @NotNull
    String name;
}

class Child extends Person {
    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}