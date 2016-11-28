package jsf.sandbox.view;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloWorld {

    private String firstName = "JSF";
    private String lastName = "PrimeFaces";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String showGreeting() {
        return "Hello" + " " + firstName + " " + lastName + "!";
    }
}
