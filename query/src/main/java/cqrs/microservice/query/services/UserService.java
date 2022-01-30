package cqrs.microservice.query.services;

import cqrs.microservice.query.entities.User;
import cqrs.microservice.query.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(String id){
            return this.userRepository.findById(id).orElse(null);
        }

    public void addUser(User user){
        this.userRepository.save(user);
    }

    public void removeUser(User user){
        this.userRepository.delete(user);
    }

    public List<User> purgeDb() {
        this.userRepository.findAll().forEach(this.userRepository::delete);
        return this.userRepository.findAll();
    }
}
