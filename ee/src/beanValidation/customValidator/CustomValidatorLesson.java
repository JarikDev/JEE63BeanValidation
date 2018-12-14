package beanValidation.customValidator;

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
import javax.validation.constraints.Email;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@WebServlet("/customValidator")
public class CustomValidatorLesson extends HttpServlet {
    @Inject
    Validator validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ConstraintViolation<Person>> email = validator.validateValue(Person.class, "email", "mymail@gmail.com");
        for (ConstraintViolation violation : email) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        Set<ConstraintViolation<Person>> email1 = validator.validateValue(Person.class, "email", "mymail@gmail.ru");
        for (ConstraintViolation violation : email1) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        //for custom made validator
        Set<ConstraintViolation<Person>> site = validator.validateValue(Person.class, "site", "habrahaabr");
        for (ConstraintViolation violation : site) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        Set<ConstraintViolation<Person>> site2 = validator.validateValue(Person.class, "site2", "https://mysite.com");
        for (ConstraintViolation violation : site2) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        Set<ConstraintViolation<Person>> site3 = validator.validateValue(Person.class, "site3", "https://othersite.com");
        for (ConstraintViolation violation : site3) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }

        Set<ConstraintViolation<Person>> site4 = validator.validateValue(Person.class, "site4", "ftp://mysite.com:22");
        for (ConstraintViolation violation : site4) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
        }




    }
}

class Person {
    @CheckEmail
    String email;
    @CheckSiteURL
    String site;

    @CheckSiteURL(protocol = "http")
    String site2;

    @CheckSiteURL(host = "mysite.com")
    String site3;

    @CheckSiteURL(protocol = "ftp", port = 21)
    String site4;
}

class CheckSiteLogic implements ConstraintValidator<CheckSiteURL, String> {
    String protocol;
    String host;
    int port;

    @Override
    public void initialize(CheckSiteURL constraintAnnotation) {
        protocol = constraintAnnotation.protocol();
        host = constraintAnnotation.host();
        port = constraintAnnotation.port();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null || s.equals("")) {
            return true;
        }
        URL url;
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            return false;
        }

        if (protocol != null && protocol.length() > 0 && !protocol.equals(url.getProtocol())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("protocol invalid").addConstraintViolation();
            return false;
        }
        if (host != null && host.length() > 0 && !host.equals(url.getHost())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("host invalid").addConstraintViolation();
            return false;
        }

        if (port != -1 && port!=url.getPort() ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("port invalid").addConstraintViolation();
            return false;
        }

        return false;
    }
}