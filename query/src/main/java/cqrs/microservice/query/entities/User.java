package cqrs.microservice.query.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "users")
public class User {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text, name = "nom")
    private String nom;

    @Field(type = FieldType.Text, name = "prenom")
    private String prenom;

}

