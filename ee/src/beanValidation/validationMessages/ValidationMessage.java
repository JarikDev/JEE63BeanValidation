package beanValidation.validationMessages;


import beanValidation.classValidation.ChronDates;

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
import java.net.MalformedURLException;
import java.util.Set;

@WebServlet("/validationMessage")
public class ValidationMessage extends HttpServlet {
    @Inject
    Validator validator;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ConstraintViolation<Person>> name = validator.validateValue(Person.class, "url", "http://host.com:23");
        for (ConstraintViolation violation : name) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }
    }
}
class Person{
    @URL(port=22)
    String url;
}

class URLValidation implements ConstraintValidator<URL, String>{
    String url;
    int port;
    @Override
    public void initialize(URL constraintAnnotation) {
        url=constraintAnnotation.url();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null || s.equals("")) {
            return true;
        }
        java.net.URL url;
        try {
            url = new java.net.URL(s);
        } catch (MalformedURLException e) {
            return false;
        }

        if (port != -1 && port!=url.getPort() ) {
            return false;
        }

        return false;
    }
}