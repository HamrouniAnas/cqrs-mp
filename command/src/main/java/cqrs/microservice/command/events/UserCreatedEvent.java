package cqrs.microservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserCreatedEvent {
    private final String id;
    private final String nom;
    private final String prenom;
}
