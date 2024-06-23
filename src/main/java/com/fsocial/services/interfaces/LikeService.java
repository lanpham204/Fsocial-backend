package com.fsocial.services.interfaces;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAll();
    Like getById(String id) throws DataNotFoundException;
    Like getByPost(String postId) throws DataNotFoundException;
    Like getByUser(String userId) throws DataNotFoundException;
    Like create(LikeDTO likesDTO);
    Like update(LikeDTO likesDTO, String id) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
