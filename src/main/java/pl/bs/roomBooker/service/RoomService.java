package pl.bs.roomBooker.domain.room;

import org.springframework.stereotype.Service;
import pl.bs.roomBooker.api.msg.RoomMsg;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    public List<Room> getAllRooms() {
        return new ArrayList<>();
    }

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundRoomException(String.format("Room %s does not exist", id)));
    }

    public void create(RoomMsg roomMsg) {
        //if()
        roomRepository.create(new Room(roomMsg.getId(), roomMsg.getName()));
    }

    public void delete(Long id) {
        roomRepository.delete(id);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

}
