package cqrs.microservice.query.queues;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteUserQueue {
    public static final String TOPIC_EXCHANGE_NAME = "delete-user-queue";
    public static final String QUEUE_NAME = "delete-user-queue";

    @Bean
    Queue deleteQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange deleteExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding deleteBinding(Queue deleteQueue, TopicExchange deleteExchange) {
        return BindingBuilder.bind(deleteQueue).to(deleteExchange).with("delete.user.#");
    }
}
