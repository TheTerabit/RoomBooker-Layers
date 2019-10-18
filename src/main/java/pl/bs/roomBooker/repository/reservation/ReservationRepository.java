package pl.bs.roomBooker.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.reservation.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
