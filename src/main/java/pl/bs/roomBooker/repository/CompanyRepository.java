package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String companyName);
}
