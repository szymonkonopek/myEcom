package pl.konopek.sales.reservation;

import java.util.HashMap;
import java.util.Optional;

public class InMemoryReservationStorage {

    HashMap<String, Reservation> reservations;

    public InMemoryReservationStorage() {
        this.reservations = new HashMap<>();
    }

    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public Optional<Reservation> load(String reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }
}
