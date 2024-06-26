package com.fsocial.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LikeResponse {

    String id;

    String  postId;

    String userId;

}
