package com.fsocial.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    String id;

    String postId;

    String senderId;

    String receiverId;

    String type;

    String description;

    String link;

    Date createAt;
}
