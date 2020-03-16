package pl.bs.roomBooker.controllers;

import org.springframework.web.bind.annotation.*;
import pl.bs.roomBooker.controllers.msg.UserMsg;
import pl.bs.roomBooker.models.user.Company;
import pl.bs.roomBooker.models.user.User;
import pl.bs.roomBooker.service.user.UserService;

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
        return userService.getAll();
    }

    @PostMapping
    public void create(@RequestBody UserMsg userMsg) {
        userService.create(userMsg);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/new")
    public List<Company> findAllCompanies(){
        return userService.getAllCompanies();
    }
}
