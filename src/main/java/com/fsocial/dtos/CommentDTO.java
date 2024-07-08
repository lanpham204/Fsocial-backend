package com.fsocial.dtos;

import com.fsocial.models.Post;
import com.fsocial.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    String comment;
    String image;

    String user;
    String post;
}
