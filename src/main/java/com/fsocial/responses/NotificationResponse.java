package com.fsocial.responses;

import com.fsocial.dtos.UserIdDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NotificationResponse {
    String id;

    String postId;

    String userSendId;

    List<UserIdDTO> userReceiver;

    String type;

    String description;

    String link;

    LocalDateTime createAt;
}
