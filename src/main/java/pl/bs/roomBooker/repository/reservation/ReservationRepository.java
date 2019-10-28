package pl.bs.roomBooker.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;

import java.time.ZonedDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("Update Reservation r set r.topic = ?2, r.roomId = ?3, r.start = ?4, r.end = ?5 where r.reservationId = ?1")
    void update(Long id, String topic, Long roomId, ZonedDateTime start, ZonedDateTime end);
}
