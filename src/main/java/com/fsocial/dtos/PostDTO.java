package com.fsocial.dtos;

import com.fsocial.models.File;
import com.fsocial.models.Major;
import com.fsocial.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @NotBlank(message = "Content cannot be blank")
    private String content;
    @NotBlank(message = "User ID cannot be blank")
    private String userId;
    private List<MultipartFile> filesMulti;
    private List<MultipartFile> imagesMulti;
    @NotBlank(message = "Major ID cannot be blank")
    private String majorId;
    private boolean isActive = false;

}
