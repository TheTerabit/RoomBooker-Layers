package pl.bs.roomBooker.controllers.msg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RoomMsg {
    private final Long id;
    private final String name;
    private final Integer roomSize;
    private final Integer floor;
    private final String sector;

    @JsonCreator
    public RoomMsg(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("roomSize") Integer roomSize,
                   @JsonProperty("floor") Integer floor,
                   @JsonProperty("sector") String sector) {
        this.id = id;
        this.name = name;
        this.roomSize = roomSize;
        this.floor = floor;
        this.sector = sector;
    }
}
