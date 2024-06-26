package com.fsocial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_rooms")
@Builder
public class ChatRoom {
    @Id
    private String id;
    @DBRef
    private User sender;
    @DBRef
    private User receiver;
    private List<Message> messages;
}
