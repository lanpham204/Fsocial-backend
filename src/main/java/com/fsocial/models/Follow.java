package com.fsocial.models;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document ("follows")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Follow {
    @Id
    String id;
    @DBRef
    User user;
    @DBRef
    User follower;
}
