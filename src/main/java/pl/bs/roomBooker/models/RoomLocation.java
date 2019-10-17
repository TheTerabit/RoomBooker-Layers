package pl.bs.roomBooker.models;


import javax.persistence.*;


@Entity
public class RoomLocation {

    @Id
    @Column(name = "id")
    private Long roomId;
    private Integer floor;
    private String sector;


    public RoomLocation(Long roomId,
                        Integer floor,
                        String sector) {
        this.roomId = roomId;
        this.floor = floor;
        this.sector = sector;
    }

    public RoomLocation() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getSector() {
        return sector;
    }
}
