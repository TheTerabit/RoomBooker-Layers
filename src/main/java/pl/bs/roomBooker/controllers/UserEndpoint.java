package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.RoomMsg;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.Room;
import pl.bs.roomBooker.models.User;
import pl.bs.roomBooker.service.RoomService;
import pl.bs.roomBooker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public void create(@RequestBody UserMsg userMsg) {
        this.userService.create(userMsg);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
    }
}