package com.fsocial.responses;

import com.fsocial.models.File;
import com.fsocial.models.Image;
import com.fsocial.models.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListResponse {
    List<PostResponse> posts;
    int totalPage;
}
