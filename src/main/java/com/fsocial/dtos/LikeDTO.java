package com.fsocial.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {

    @NotBlank(message = "Post can not blank")
    String postId;

    @NotBlank(message = "User can not blank")
    String userId;
}
