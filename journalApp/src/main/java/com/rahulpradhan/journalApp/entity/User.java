package com.rahulpradhan.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "Users")
@Data
public class User {

  @Id
  private ObjectId Id;

  @Indexed(unique = true)
  @NonNull
  private String userName;

  @NonNull
  private String password;

  @DBRef
  private List<JournalEntry> journalEntries = new ArrayList<>();

}
