package pl.konopek.web;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class SessionCurrentCustomerContext implements CurrentCustomerContext {
    HttpSession httpSession;

    public SessionCurrentCustomerContext(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public String getCurrentCustomerId() {
        if (httpSession.getAttribute("current_customer_id") == null) {
            String id = UUID.randomUUID().toString();
            httpSession.setAttribute("current_customer_id", id);
            return id;
        }

        return (String) httpSession.getAttribute("current_customer_id");
    }
}
