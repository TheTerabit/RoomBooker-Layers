package pl.bs.roomBooker.domain.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Objects;


@Entity
public class Room {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private Long roomId;
    private String name;
    private Integer roomSize;
    private RoomLocation roomLocation;

    public Room(Long roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) &&
                Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, name);
    }


}
