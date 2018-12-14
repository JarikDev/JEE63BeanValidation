package beanValidation.beanValidation;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/beanValidationBasic")
public class BeanValidationBasic extends HttpServlet {
    @Inject
    Person person;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(person.name + " ");

    }
}

class Person {
    @NotNull
    @Pattern.List({
            @Pattern(regexp = "[A-Z][a-z]*"),
            @Pattern(regexp = "")
    })
    String name;
    @Min(18)
    int age;
    @Size(max = 200, min = 100)
    String description;
    @Past
    Date birth;
    @Future
    Date weddingDate;

    String sername;

    @NotNull
    public String getSername() {
        return sername;
    }

    public void setSername(@NotNull @Pattern(regexp = "[A-z]") String sername) {
        this.sername = sername;
    }


}

