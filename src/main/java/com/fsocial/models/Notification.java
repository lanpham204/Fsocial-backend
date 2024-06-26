package com.fsocial.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Notification {
    @Id
    String id;

    @DBRef
    Post post;

    @DBRef
    User userSend;

    @DBRef
    List<User> userReceiver;

    TypeNotification type;

    String description;

    String link;

    LocalDateTime createAt;

}
