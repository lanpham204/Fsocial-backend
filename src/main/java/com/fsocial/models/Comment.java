package com.fsocial.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    String id;
    String comment;
    String image;
    LocalDateTime commentAt;
    @DBRef
    User user;
    @DBRef
    Post post;

}
