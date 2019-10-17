package pl.bs.roomBooker.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Room {

    @Id
    @SequenceGenerator(name = "myRoomSeqGen", sequenceName = "myRoomSeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myRoomSeqGen")
    @Column(name = "id")
    private Long roomId;
    private String name;
    private Integer roomSize;
    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private RoomLocation roomLocation;


    public Room(Long roomId, String name, Integer roomSize) {
        this.roomId = roomId;
        this.name = name;
        this.roomSize = roomSize;
    }

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public RoomLocation getRoomLocation() {
        return roomLocation;
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
