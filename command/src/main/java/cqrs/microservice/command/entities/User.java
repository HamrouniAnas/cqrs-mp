package cqrs.microservice.command.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Users")
public class User {
    @Id
    private String nom;
    private String prenom;
}
