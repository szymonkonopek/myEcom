package pl.konopek.sales.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository
        extends JpaRepository<Reservation, String> {
}