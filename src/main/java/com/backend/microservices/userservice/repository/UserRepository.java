package com.backend.microservices.userservice.repository;

import com.backend.microservices.userservice.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

//    @Query("{Category.name:?0")
//    List<User> findByCategory(String category);

//    List<User> findByCategoryName(String category);
}
