package com.fsocial.responses;

import com.fsocial.models.Post;
import com.fsocial.models.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponses {
    String id;
    String comment;
    String image;
    LocalDateTime commentAt;
    User userId;
    Post postId;

}
