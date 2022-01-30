package cqrs.microservice.command.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.events.UserDeletedEvent;
import cqrs.microservice.command.events.UserCreatedEvent;
import cqrs.microservice.command.entities.User;
import cqrs.microservice.command.repositories.UserRepository;
import cqrs.microservice.command.senders.DeleteUserSender;
import cqrs.microservice.command.senders.CreateUserSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final CreateUserSender createUserSender;
    private final DeleteUserSender deleteUserSender;

    public UserService(UserRepository userRepository, CreateUserSender createUserSender,
                       DeleteUserSender deleteUserSender) {
        this.userRepository = userRepository;
        this.createUserSender = createUserSender;
        this.deleteUserSender = deleteUserSender;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public String createUser(User user) throws JsonProcessingException {
        User checkUser = this.userRepository.findByIdentif(user.getId());
        if (checkUser != null) {
            return "The user's id exists already!";
        }

        User createdUser = this.userRepository.save(user);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                user.getId(),
                user.getNom(),
                user.getPrenom()
        );
        this.createUserSender.send(userCreatedEvent);
        return createdUser.getNom() + " has been created!";
    }

    public String deleteUser(String id) throws JsonProcessingException {
        User deletedUser = this.userRepository.findByIdentif(id);

        if (deletedUser == null) {
            return "User does not exist!";
        }

        this.userRepository.delete(deletedUser);
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent(
                deletedUser.getId()
        );
        this.deleteUserSender.send(userDeletedEvent);
        return "Congrats! you deleted " + deletedUser.getNom();

    }

    public List<User> purgeDb() {
        this.userRepository.findAll().forEach(this.userRepository::delete);
        return this.userRepository.findAll();
    }
}
