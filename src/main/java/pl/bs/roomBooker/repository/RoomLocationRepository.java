package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.RoomLocation;

@Repository
public interface RoomLocationRepository extends JpaRepository<RoomLocation, Long> {
}
