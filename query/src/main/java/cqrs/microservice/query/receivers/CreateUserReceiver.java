package cqrs.microservice.query.receivers;

import cqrs.microservice.query.events.UserCreatedEvent;
import cqrs.microservice.query.entities.User;
import cqrs.microservice.query.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class CreateUserReceiver {
    UserService userService;

    public CreateUserReceiver(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "create-user-queue")
    public void receiveMessage(Message<UserCreatedEvent> message) {
        User user = new User(
                message.getPayload().getId(),
                message.getPayload().getNom(),
                message.getPayload().getPrenom()
        );
        userService.addUser(user);
    }
}
