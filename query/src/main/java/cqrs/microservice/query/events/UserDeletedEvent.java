package cqrs.microservice.query.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDeletedEvent {
    private String id;
}
