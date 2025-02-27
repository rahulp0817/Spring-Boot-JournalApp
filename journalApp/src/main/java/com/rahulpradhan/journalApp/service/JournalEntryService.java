package com.rahulpradhan.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rahulpradhan.journalApp.entity.JournalEntry;
import com.rahulpradhan.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
  
  @Autowired
  private JournalEntryRepository journalEntryRepository;
  
  public List<JournalEntry> getAll() {
    return journalEntryRepository.findAll();
  }
  
  public void saveEntry(JournalEntry JournalEntry) {
    journalEntryRepository.save(JournalEntry);
  }

  public JournalEntry getEntryById(ObjectId id) {
    return journalEntryRepository.findById(id).orElse(null);
  }

  public void deleteById(ObjectId id) {
    journalEntryRepository.deleteById(id);
  }

}


// Contoller -> Service -> Repository
