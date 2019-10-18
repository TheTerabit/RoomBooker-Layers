package pl.bs.roomBooker.service;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.controllers.msg.RoomMsg;
import pl.bs.roomBooker.models.room.Room;
import pl.bs.roomBooker.models.room.RoomLocation;
import pl.bs.roomBooker.repository.room.RoomLocationRepository;
import pl.bs.roomBooker.repository.room.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomLocationRepository roomLocationRepository;

    public RoomService(RoomRepository roomRepository, RoomLocationRepository roomLocationRepository) {
        this.roomRepository = roomRepository;
        this.roomLocationRepository = roomLocationRepository;
    }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundRoomException(String.format("Room %s does not exist", id)));
    }

    public void create(RoomMsg roomMsg) {
        //if()
        Room room = new Room(roomMsg.getId(),
                roomMsg.getName(),
                roomMsg.getRoomSize());
        roomRepository.save(room);

        roomLocationRepository.save(new RoomLocation(room.getRoomId(),
                                                roomMsg.getFloor(),
                                                roomMsg.getSector()));
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

}
