package pl.bs.roomBooker.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.room.RoomLocation;

@Repository
public interface RoomLocationRepository extends JpaRepository<RoomLocation, Long> {
}
