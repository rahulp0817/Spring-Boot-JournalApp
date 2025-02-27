package com.rahulpradhan.journalApp.controller;

import java.time.LocalDateTime;
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
import com.rahulpradhan.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;
  
  @GetMapping
  public ResponseEntity<?> getAll() {
    List<JournalEntry> all = journalEntryService.getAll();
    if (all != null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
    try{
      myEntry.setDate(LocalDateTime.now());
      journalEntryService.saveEntry(myEntry);
      return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    } catch(Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{myEntryId}")
  public ResponseEntity<JournalEntry> getJournalEntries(@PathVariable ObjectId myEntryId) {

    JournalEntry journalEntry = journalEntryService.getEntryById(myEntryId);

    if (journalEntry != null) {
      return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }
  
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{myEntryId}")
  public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myEntryId) {
    journalEntryService.deleteById(myEntryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
    JournalEntry presentData = journalEntryService.getEntryById(id);

    if (presentData != null) {

      if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
          presentData.setTitle(newEntry.getTitle());
      }

      if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
          presentData.setContent(newEntry.getContent());
      }

      journalEntryService.saveEntry(presentData);
      return new ResponseEntity<>(presentData, HttpStatus.OK);
  }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
