package cqrs.microservice.query.events;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreatedEvent {
    private String id;
    private String nom;
    private String prenom;
}
