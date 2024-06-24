package com.fsocial.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsocial.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateInfoDto {
    @NotBlank(message = "Full name cannot be blank")
    @JsonProperty("full_name")
    String fullName;

    String avatar;

    String background;

    @NotNull(message = "Active status cannot be null")
    @JsonProperty("is_active")
    Boolean isActive;

    @NotNull(message = "Non-locked status cannot be null")
    @JsonProperty("non_locked")
    Boolean nonLocked;

    @NotNull(message = "Reward points cannot be null")
    @JsonProperty("reward_points")
    Integer rewardPoints;

    String role;
    }
