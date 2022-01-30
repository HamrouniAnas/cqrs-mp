package cqrs.microservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDeletedEvent {
    private final String id;
}
