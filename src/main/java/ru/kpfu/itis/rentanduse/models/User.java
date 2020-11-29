package ru.kpfu.itis.rentanduse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import org.jongo.marshall.jackson.oid.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @ObjectId
    @Id
    private String _id;

    private int age;
    private String city;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private List<String> role;
}
