package ru.kpfu.itis.rentanduse.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.ObjectIdGenerator;
import org.bson.types.ObjectId;
import ru.kpfu.itis.rentanduse.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class UsersRepositoryMongoDriverImpl implements UsersRepository {
    private MongoClient client;
    private MongoDatabase database;
    MongoCollection<Document> collection;
    private ObjectMapper objectMapper;

    public UsersRepositoryMongoDriverImpl() {
        client = MongoClients.create();
        database = client.getDatabase("rent_and_use");
        collection = database.getCollection("users");
        objectMapper = new ObjectMapper();
    }

    @Override
    public Optional<User> find(String id) {
        Document document = collection.find(eq("_id", new ObjectId(id))).first();
        if (document == null) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(objectMapper.readValue(document.toJson(), User.class));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            try {
                users.add(objectMapper.readValue(document.toJson(), User.class));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            Document userInDocument = Document.parse(objectMapper.writeValueAsString(user));
            if (userInDocument.getObjectId("_id") == null) {
                userInDocument.put("_id", new ObjectIdGenerator().generate());
            }
            collection.insertOne(userInDocument);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(String id) {
        collection.deleteOne(eq("_id", new ObjectId(id)));
    }

    @Override
    public void update(User user) {
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.set("age", user.getAge()));
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.set("city", user.getCity()));
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.set("firstName", user.getFirstName()));
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.set("lastName", user.getLastName()));
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.set("registrationDate", user.getRegistrationDate()));
        collection.updateOne(Filters.eq("_id", user.get_id()), Updates.addEachToSet("role", user.getRole()));
    }

}
