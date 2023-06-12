package pl.konopek.sales.reservation;

public class ClientData {

    private final String firstname;
    private final String lastname;
    private final String email;

    public ClientData(String firstname, String lastname, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
