package pl.bs.roomBooker.models.reservation;

import pl.bs.roomBooker.models.user.Company;
import pl.bs.roomBooker.models.user.UserAddress;
import pl.bs.roomBooker.models.user.UserContact;
import pl.bs.roomBooker.models.user.UserPassword;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Reservation {

    @Id
    @SequenceGenerator(name = "myReservationGen", sequenceName = "myReservationSeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myReservationGen")
    @Column(name = "id")
    private Long reservationId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "roomId")
    private Long roomId;

    private ZonedDateTime start;
    private ZonedDateTime end;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private Topic topic;

    public Reservation() {
    }

    public Reservation(Long userId, Long roomId, ZonedDateTime start, ZonedDateTime end) {
        this.userId = userId;
        this.roomId = roomId;
        this.start = start;
        this.end = end;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
/*
reservations
- reservationId
-	userId
-	roomId
-	topicId
-	startTimeStamp
-	endTimeStamp

topics
-	topicId
-	topicName
-   category

*/