package com.rahulpradhan.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulpradhan.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  // Database
  private Map<Long, JournalEntry> journalEntries = new HashMap<>();
  
  @GetMapping
  public List<JournalEntry> getAll() {
    return new ArrayList<>(journalEntries.values());
  }

  @PostMapping
  public boolean createEntry(@RequestBody JournalEntry myEntry) {
    journalEntries.put(myEntry.getId(), myEntry);
    return true;
  }

  @GetMapping("/id/{myEntryId}")
  public JournalEntry getJournalEntries(@PathVariable Long myEntryId) {
    return journalEntries.get(myEntryId);
  }

  @DeleteMapping("/id/{myEntryId}")
  public JournalEntry deleteJournalEntry(@PathVariable Long myEntryId) {
    return journalEntries.remove(myEntryId);
  }

  @PutMapping("/id/{id}")
  public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
    return journalEntries.put(id, myEntry);
  }

}
