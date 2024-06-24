package com.fsocial.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsocial.models.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
        String id;
        @JsonProperty("full_name")
        String fullName;
        String email;
        String avatar;
        String background;
        @JsonProperty("is_active")
        boolean isActive;
        @JsonProperty("non_locked")
        boolean nonLocked=true;
        @JsonProperty("reward_points")
        int rewardPoints;
        Role role;
}
