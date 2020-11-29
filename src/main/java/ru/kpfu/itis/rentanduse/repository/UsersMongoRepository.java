package ru.kpfu.itis.rentanduse.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.kpfu.itis.rentanduse.models.User;

import java.util.List;

public interface UsersMongoRepository extends MongoRepository<User, String> {

    @RestResource(path = "findByRole", rel = "findByRole")
    List<User> findUsersByRoleContains(@Param("role")String role, Pageable pageable);
}
