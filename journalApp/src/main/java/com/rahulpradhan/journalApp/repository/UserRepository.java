package com.rahulpradhan.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rahulpradhan.journalApp.entity.User;


public interface UserRepository extends MongoRepository<User, ObjectId>{
  User findByUserName(String userName);
}
