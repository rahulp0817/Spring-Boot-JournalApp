package com.rahulpradhan.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rahulpradhan.journalApp.entity.JournalEntry;
import com.rahulpradhan.journalApp.entity.User;
import com.rahulpradhan.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
  
  @Autowired
  private JournalEntryRepository journalEntryRepository;
  
  @Autowired
  private UserService userService;
  
  public List<JournalEntry> getAll() {
    return journalEntryRepository.findAll();
  }
  
  @Transactional
  public void saveEntry(JournalEntry journalEntry, String userName) {
    try {
      User user = userService.findByuserName(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry saved = journalEntryRepository.save(journalEntry);
      user.getJournalEntries().add(saved);
      userService.saveEntry(user);
    } catch (Exception e) {
      throw new RuntimeException("Failed to save journal entry", e);
    }
  }

  public JournalEntry getEntryById(ObjectId id) {
    return journalEntryRepository.findById(id).orElse(null);
  }

  public void deleteById(ObjectId id, String userName) {
    User user = userService.findByuserName(userName);
    user.getJournalEntries().removeIf(j -> j.getId().equals(id));
    userService.saveEntry(user);
    journalEntryRepository.deleteById(id);
  }

}


// Contoller -> Service -> Repository
