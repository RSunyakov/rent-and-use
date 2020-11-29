package ru.kpfu.itis.rentanduse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.rentanduse.models.User;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UsersRepositoryMongoTemplateImpl implements UsersRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<User> find(String id) {
        List<User> userList = mongoTemplate.find(new Query(where("_id").is(id)), User.class, "users");
        if (userList.size() == 0) {
            return Optional.empty();
        } else return Optional.of(userList.get(0));
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class, "users");
    }

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void delete(String id) {
        mongoTemplate.remove(new Query(where("_id").is(id)),User.class, "users");
    }

    @Override
    public void update(User user) {
        mongoTemplate.save(user);
    }
}
