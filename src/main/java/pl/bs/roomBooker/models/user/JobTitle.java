package pl.bs.roomBooker.models.user;

import javax.persistence.*;

@Entity
public class JobTitle {

    @Id
    @SequenceGenerator(name = "myJobTitleSeqGen", sequenceName = "myJobTitleSeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myJobTitleSeqGen")
    @Column(name = "jobTitleId")
    private Long jobId;
    @Column(unique=true)
    private String jobName;

    public JobTitle() {
    }

    public JobTitle(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }
}
