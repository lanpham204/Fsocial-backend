package com.fsocial.models;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id @Pattern(regexp = "[Pp][Ss]\\\\d{5}", message = "The input must match the pattern 'PS' or 'ps' followed by exactly 5 digits\"")
    String id;
    String fullname;
    String email;
    String password;
    String avatar;
    String background;
    boolean is_active=false;
    boolean non_locked=true;
    int reward_points;
    @DBRef
    Role role;
}
