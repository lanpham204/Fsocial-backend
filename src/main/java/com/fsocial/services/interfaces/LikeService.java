package com.fsocial.services.interfaces;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;
import com.fsocial.responses.LikeResponse;

import java.util.List;

public interface LikeService {
    List<LikeResponse> getAll();
    LikeResponse getById(String id) throws DataNotFoundException;
    List<LikeResponse> getByPost(String postId);
    List<LikeResponse> getByUser(String userId);
    LikeResponse create(LikeDTO likesDTO) throws DataNotFoundException;
    LikeResponse update(LikeDTO likesDTO, String id) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
