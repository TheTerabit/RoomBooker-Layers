package pl.bs.roomBooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.JobTitle;

import java.util.Optional;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {


    JobTitle findByJobName(String jobTitleName);
}
