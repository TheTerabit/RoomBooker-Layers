package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.RoomMsg;
import pl.bs.roomBooker.models.Room;
import pl.bs.roomBooker.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomEndpoint {

    private final RoomService roomService;

    public RoomEndpoint(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return this.roomService.getAll();
    }

    @PostMapping
    public void create(@RequestBody RoomMsg roomMsg) {
        this.roomService.create(roomMsg);
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable("id") Long id) {
        return this.roomService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.roomService.delete(id);
    }

}
