package com.fsocial.responses;

import com.fsocial.models.Message;
import com.fsocial.models.User;
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
@Builder
public class ChatRoomResponse {
        private String id;
        private UserResponse sender;
        private UserResponse receiver;
        private List<Message> messages;
}
