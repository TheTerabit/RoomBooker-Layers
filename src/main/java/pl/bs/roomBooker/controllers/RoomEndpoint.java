package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.RoomMsg;
import pl.bs.roomBooker.models.room.Room;
import pl.bs.roomBooker.service.room.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomEndpoint {

    private final RoomService roomService;

    public RoomEndpoint(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @PostMapping
    public void create(@RequestBody RoomMsg roomMsg) {
        roomService.create(roomMsg);
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable("id") Long id) {
        return roomService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roomService.delete(id);
    }

}
