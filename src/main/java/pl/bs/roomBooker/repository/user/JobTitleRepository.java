package pl.bs.roomBooker.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bs.roomBooker.models.user.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {


    JobTitle findByJobName(String jobTitleName);
}
