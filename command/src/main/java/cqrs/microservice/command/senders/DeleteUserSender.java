package cqrs.microservice.command.senders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cqrs.microservice.command.events.UserDeletedEvent;
import cqrs.microservice.command.queues.DeleteUserQueue;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class DeleteUserSender {
    static final String ROUTING_KEY = "delete.user.";

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public DeleteUserSender(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(UserDeletedEvent userDeletedEvent) throws JsonProcessingException {
        String userDeletedEventJson = this.objectMapper.writeValueAsString(userDeletedEvent);
        Message message = MessageBuilder
                .withBody(userDeletedEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(
                DeleteUserQueue.QUEUE_NAME,
                ROUTING_KEY + userDeletedEvent.getId(),
                message
        );
    }
}