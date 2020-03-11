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
    private Long reservationId;

    @Column(name = "username")
    private String username;

    @Column(name = "room_id")
    private Long roomId;

    private ZonedDateTime start;
    private ZonedDateTime end;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Reservation() {
    }

    public Reservation(String username, Long roomId, ZonedDateTime start, ZonedDateTime end) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void SetId(Long id) {
        this.reservationId = id;
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