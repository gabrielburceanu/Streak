package com.streak.authservice;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "users")
public class UserInfo {
    @MongoId
    private ObjectId id;
    private String username;
    private String passwordHash;
}