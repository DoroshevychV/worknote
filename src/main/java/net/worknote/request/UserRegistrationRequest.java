package net.worknote.request;


public class UserRegistrationRequest{

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String repeatPassword;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String firstName, String lastName, String email, String password,String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getFirstName() {
        //first letter - upper,and the rest of the letters are small
        this.firstName = this.firstName.substring(0, 1)
                .toUpperCase() + this.firstName.substring(1).toLowerCase();
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        //first letter - upper,and the rest of the letters are small
        this.lastName = this.lastName.substring(0, 1)
                .toUpperCase() + this.lastName.substring(1).toLowerCase();
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        //all letters - small
        this.email.substring(0).toLowerCase();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }



    //method editing first or last name
    //and replaces spelling
    public boolean editNameOrLastName(String tape) {

        char testChar;
        String testString;
        for (int i = 0; i < tape.length(); i++) {

            testChar = tape.charAt(i);
            testString = Character.toString(testChar);
            if (!testString.matches("^[іІїЇa-zA-Zа-яА-Яа-яА-Я]+$")) {

                return false;
            }
        }
        return true;

    }

    public boolean emailForm(String email) {

        char testChar;
        String testString;
        if (email.contains("@")
                & email.contains(".")) {

            for (int i = 0; i < email.length(); i++) {

                testChar = email.charAt(i);
                testString = Character.toString(testChar);

                if (!testString.matches("^[-.@_0-9a-zA-Z]+$")) {

                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

}
