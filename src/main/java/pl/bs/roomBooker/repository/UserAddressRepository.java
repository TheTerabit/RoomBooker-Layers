package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
