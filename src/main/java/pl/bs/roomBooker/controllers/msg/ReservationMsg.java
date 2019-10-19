package pl.bs.roomBooker.controllers.msg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ReservationMsg {
    private final String username;
    private final String password;
    private final Long roomId;
    private final String topic;
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    @JsonCreator
    public ReservationMsg(@JsonProperty("username") String username,
                          @JsonProperty("password") String password,
                          @JsonProperty("roomId") Long roomId,
                          @JsonProperty("topic") String topic,
                          @JsonProperty("start") ZonedDateTime start,
                          @JsonProperty("end") ZonedDateTime end) {
        this.username = username;
        this.password = password;
        this.roomId = roomId;
        this.topic = topic;
        this.start = start;
        this.end = end;
    }
}