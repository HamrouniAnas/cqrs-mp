package cqrs.microservice.command.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.entities.User;

import cqrs.microservice.command.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/command")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws JsonProcessingException {
        return this.userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) throws JsonProcessingException {
        return this.userService.deleteUser(id);
    }

    @GetMapping("/")
    public List<User> getAll() {
        return this.userService.getUsers();
    }

    @DeleteMapping("/purge")
    public List<User> purgeDb(){
        return this.userService.purgeDb();
    }
}
