package com.fsocial.dtos;

import com.fsocial.models.Message;
import com.fsocial.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDTO {
        @NotBlank(message = "sender is not blank")
        private String senderId;
        @NotBlank(message = "receiver is not blank")
        private String receiverId;
        @NotBlank(message = "message is not blank")
        private String message;
}
