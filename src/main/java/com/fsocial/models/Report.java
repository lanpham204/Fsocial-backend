package com.fsocial.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("resport")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Report {
  @Id
  String id;
  User user;
  String code;
}
