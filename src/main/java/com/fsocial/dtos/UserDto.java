package com.fsocial.dtos;

import com.fsocial.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class UserDto {
    @Pattern(regexp = "[Pp][Ss]\\\\d{5}", message = "The input must match the pattern 'PS' or 'ps' followed by exactly 5 digits\"")
    String id;
    @NotBlank
    String fullName;
    @NotBlank
    String email;
    String password;
    String avatar;
    String background;
    boolean isActive=false;
    boolean nonLocked=true;
    int rewardPoints;
    @DBRef
    Role role;
}
