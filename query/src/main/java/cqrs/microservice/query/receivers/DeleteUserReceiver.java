package cqrs.microservice.query.receivers;

import cqrs.microservice.query.events.UserDeletedEvent;
import cqrs.microservice.query.entities.User;
import cqrs.microservice.query.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserReceiver {
    UserService userService;

    public DeleteUserReceiver(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "delete-user-queue")
    public void receiveMessage(Message<UserDeletedEvent> message) {
        User user = this.userService.getUserById(message.getPayload().getId());
        if (user != null) {
            userService.removeUser(user);
        }
    }
}
