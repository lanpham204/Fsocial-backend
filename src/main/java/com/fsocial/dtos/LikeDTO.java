package com.fsocial.dtos;

import com.fsocial.models.User;
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
    String id;

    String postId;

    @DBRef
    User user;
}
