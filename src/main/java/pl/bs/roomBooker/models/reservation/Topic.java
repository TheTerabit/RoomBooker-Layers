package pl.bs.roomBooker.models.reservation;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "filldb",
                procedureName = "filldb")
})
public class Topic {

    @Id
    @SequenceGenerator(name = "myTopicSeqGen", sequenceName = "myTopicSeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myTopicSeqGen")
    @Column(name = "topicId")
    private Long topicId;
    @Column(unique=true)
    private String topicName;

    public Topic() {
    }

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
