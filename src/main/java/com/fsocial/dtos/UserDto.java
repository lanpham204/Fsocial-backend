package com.fsocial.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Pattern(regexp = "[Pp][Ss]\\d{5}", message = "The input must match the pattern 'PS' or 'ps' followed by exactly 5 digits\"")
    String id;
    @JsonProperty("full_name")
    @NotBlank(message = "Full name must not be blank")
    String fullName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Password must not be blank")
    String password;
    @JsonProperty("confirm_password")
    @NotBlank(message = "Confirm Password must not be blank")
    String confirmPassword;
}
