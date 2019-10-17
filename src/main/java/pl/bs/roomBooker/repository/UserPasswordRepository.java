package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.UserPassword;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
}
