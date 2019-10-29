package pl.bs.roomBooker.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bs.roomBooker.controllers.msg.ReservationMsg;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.reservation.Topic;

import java.time.ZonedDateTime;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Modifying
    @Query("Update Reservation r set r.topic = ?2, r.roomId = ?3, r.start = ?4, r.end = ?5 where r.reservationId = ?1")
    void update(Long id, Topic topic, Long roomId, ZonedDateTime start, ZonedDateTime end);
}
