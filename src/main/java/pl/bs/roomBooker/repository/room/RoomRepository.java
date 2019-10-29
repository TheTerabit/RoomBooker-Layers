package pl.bs.roomBooker.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.reservation.Reservation;
import pl.bs.roomBooker.models.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    @Override
    Optional<Room> findById(Long id);




}
