package pl.bs.roomBooker.repository.reservation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.reservation.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByTopicName(String topic);
}
