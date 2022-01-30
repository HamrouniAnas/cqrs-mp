package cqrs.microservice.command.senders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cqrs.microservice.command.events.UserCreatedEvent;
import cqrs.microservice.command.queues.CreateUserQueue;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class CreateUserSender {
    static final String ROUTING_KEY = "create.user.";

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public CreateUserSender(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(UserCreatedEvent userCreatedEvent) throws JsonProcessingException {
        String userCreatedEventJson = this.objectMapper.writeValueAsString(userCreatedEvent);
        Message message = MessageBuilder
                .withBody(userCreatedEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(
                CreateUserQueue.QUEUE_NAME,
                ROUTING_KEY + userCreatedEvent.getId(),
                message
        );
    }
}
