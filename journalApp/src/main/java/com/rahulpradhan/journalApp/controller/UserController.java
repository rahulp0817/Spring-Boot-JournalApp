package com.rahulpradhan.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulpradhan.journalApp.entity.User;
import com.rahulpradhan.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userservice; 

  @GetMapping
  public List<User> getAll() {
    return userservice.getAll();
  }

  @PostMapping
  public void createEntry(@RequestBody User user) {
    userservice.saveEntry(user);
  }

  @PutMapping("/{userName}")
  public ResponseEntity<?> Updateuser(@PathVariable String userName, @RequestBody User user) {
    User existingUser = userservice.findByuserName(user.getUserName());
    if (existingUser != null) {
      existingUser.setUserName(user.getUserName());
      existingUser.setPassword(user.getPassword());
      userservice.saveEntry(existingUser);
      return ResponseEntity.ok("User updated successfully");
    } else {
      return ResponseEntity.status(404).body("User not found");
    }
  }

}
