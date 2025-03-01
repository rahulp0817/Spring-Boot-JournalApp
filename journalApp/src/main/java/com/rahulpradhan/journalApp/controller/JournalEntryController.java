package com.rahulpradhan.journalApp.controller;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulpradhan.journalApp.entity.JournalEntry;
import com.rahulpradhan.journalApp.entity.User;
import com.rahulpradhan.journalApp.service.JournalEntryService;
import com.rahulpradhan.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;
  
  @GetMapping("{userName}")
  public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {

      User user = userService.findByuserName(userName);

      if (user == null) {
          return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
      }

      List<JournalEntry> all = user.getJournalEntries();

      if (all != null && !all.isEmpty()) {
          return new ResponseEntity<>(all, HttpStatus.OK);
      }

      return new ResponseEntity<>("No journal entries found for the user", HttpStatus.NOT_FOUND);
  }


  @PostMapping("{userName}")
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
    try{
      journalEntryService.saveEntry(myEntry, userName);
      return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    } catch(Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("id/{myEntryId}")
  public ResponseEntity<JournalEntry> getJournalEntries(@PathVariable ObjectId myEntryId) {

    JournalEntry journalEntry = journalEntryService.getEntryById(myEntryId);

    if (journalEntry != null) {
      return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }
  
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{userName}/{myEntryId}")
  public ResponseEntity<?> deleteJournalEntry(@PathVariable String userName, @PathVariable ObjectId myEntryId) {
    journalEntryService.deleteById(myEntryId, userName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("id/{userName}/{myEntryId}")
  public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId myEntryId, @RequestBody JournalEntry newEntry, @PathVariable String userName) {
    JournalEntry presentData = journalEntryService.getEntryById(myEntryId);

    if (presentData != null) {  

      if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
          presentData.setTitle(newEntry.getTitle());
      }

      if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
          presentData.setContent(newEntry.getContent());
      }

      journalEntryService.saveEntry(presentData, userName);
      return new ResponseEntity<>(presentData, HttpStatus.OK);
  }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
