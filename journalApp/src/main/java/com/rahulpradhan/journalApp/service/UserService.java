package com.rahulpradhan.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.rahulpradhan.journalApp.entity.User;
import com.rahulpradhan.journalApp.repository.UserRepository;

@Component
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }
  
  public void saveEntry(User user) {
    userRepository.save(user);
  }

  public User getEntryById(ObjectId id) {
    return userRepository.findById(id).orElse(null);
  }

  public void deleteById(ObjectId id) {
    userRepository.deleteById(id);
  }

  public User findByuserName(String userName) {
    return userRepository.findByUserName(userName);
  }

}
