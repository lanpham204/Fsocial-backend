package com.fsocial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
@Document("posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    private String id;
    private String content;
    @DBRef
    private User user;
    private List<File> files;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @DBRef
    private Major major;
    private boolean isActive;

}
