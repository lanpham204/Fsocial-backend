package com.fsocial.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    String id;

    String postId;

    String userSendId;

    List<String> userReceiverId;

    String type;

    String description;

    String link;

    LocalDateTime createAt = LocalDateTime.now();

}
