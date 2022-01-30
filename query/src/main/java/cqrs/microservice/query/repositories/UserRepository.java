package cqrs.microservice.query.repositories;

import cqrs.microservice.query.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.lang.NonNull;

import java.util.List;


public interface UserRepository extends ElasticsearchRepository<User, String> {
    @NonNull
    List<User> findAll();
}
