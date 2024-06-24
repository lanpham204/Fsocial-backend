package com.fsocial.dtos;

import com.fsocial.models.Post;
import com.fsocial.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {

    @NotBlank(message = "Post can not blank")
    Post post;

    @NotBlank(message = "User can not blank")
    User user;
}
