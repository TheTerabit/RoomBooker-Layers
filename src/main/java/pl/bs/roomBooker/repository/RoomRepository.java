package pl.bs.roomBooker.infrastructure.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bs.roomBooker.domain.room.Room;

import java.util.Optional;

public interface SpringJpaRoomRepository extends JpaRepository<Room, Long> {
    @Override
    Optional<Room> findById(Long id);


}
