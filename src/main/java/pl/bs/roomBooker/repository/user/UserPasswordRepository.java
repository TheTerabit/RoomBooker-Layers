package pl.bs.roomBooker.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.user.UserPassword;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
}
