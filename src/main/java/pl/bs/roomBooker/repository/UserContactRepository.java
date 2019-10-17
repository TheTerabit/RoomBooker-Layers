package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.UserContact;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
}
