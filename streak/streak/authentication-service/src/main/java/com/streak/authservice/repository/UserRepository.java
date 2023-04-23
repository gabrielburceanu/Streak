package com.streak.authservice.repository;

import com.streak.authservice.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByUsername(String username);
    List<UserInfo> findAll();
}