package pl.konopek.sales.reservation;

public class OfferAcceptanceRequest {
    String firstname;
    String lastname;
    String email;

    public String getFirstname() {
        return firstname;
    }

    public OfferAcceptanceRequest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public OfferAcceptanceRequest setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OfferAcceptanceRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
