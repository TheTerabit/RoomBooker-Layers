package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.Room;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    @Override
    Optional<Room> findById(Long id);


}
