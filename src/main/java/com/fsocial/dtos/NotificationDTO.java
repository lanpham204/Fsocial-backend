package com.fsocial.dtos;

import com.fsocial.models.Post;
import com.fsocial.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {

    @NotBlank(message = "Post can not blank")
    String postId;

    @NotBlank(message = "Sender can not blank")
    String userSendId;

    @NotBlank(message = "Receiver can not blank")
    List<String> userReceiver;

    @NotBlank(message = "Post can not blank")
    String type;

    String description;

    String link;


}
