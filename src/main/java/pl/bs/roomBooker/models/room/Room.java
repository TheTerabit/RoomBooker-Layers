package pl.bs.roomBooker.models.room;

import pl.bs.roomBooker.models.reservation.Reservation;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="roomId", referencedColumnName = "id")
    private List<Reservation> reservations;


    public Room(String name, Integer roomSize) {
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

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
    }

    public void setRoomLocation(RoomLocation roomLocation) {
        this.roomLocation = roomLocation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
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
