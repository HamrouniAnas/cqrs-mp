package cqrs.microservice.query.controllers;

import cqrs.microservice.query.entities.User;
import cqrs.microservice.query.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/query")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return this.userService.getUserById(id);
    }

    @DeleteMapping("/purge")
    public List<User> purgeDb(){
        return this.userService.purgeDb();
    }
}
