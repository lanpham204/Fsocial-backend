package com.fsocial.responses;

import com.fsocial.models.File;
import com.fsocial.models.Image;
import com.fsocial.models.Major;
import com.fsocial.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String content;
    private String userId;
    private List<File> files;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String majorId;
    private boolean active;
}
