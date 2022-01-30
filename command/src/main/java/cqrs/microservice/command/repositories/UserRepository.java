package cqrs.microservice.command.repositories;

import cqrs.microservice.command.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByIdentif(String id);
}
