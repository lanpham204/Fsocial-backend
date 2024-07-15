package com.fsocial.services.interfaces;

import com.fsocial.dtos.PostDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.PostListResponse;
import com.fsocial.responses.PostResponse;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface PostService {
  PostResponse create(PostDTO postDTO) throws DataNotFoundException, IOException;

  PostListResponse getPosts(Pageable pageable);
  PostListResponse getPostsByActiveTrue(Pageable pageable);
  PostListResponse getPostsByActiveFalse(Pageable pageable);

  PostResponse update(PostDTO postDTO, String id) throws DataNotFoundException;

  PostResponse getById(String id) throws DataNotFoundException;

  List<PostResponse> getByUserId(String userId) throws DataNotFoundException;

  PostListResponse getByMajorId(String majorId, Pageable pageable) throws DataNotFoundException;

  void delete(String id) throws DataNotFoundException;
}
