package com.fsocial.services.interfaces;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAll();
    Like getById(String id) throws DataNotFoundException;
    List<Like> getByPost(String postId);
    List<Like> getByUser(String userId);
    Like create(LikeDTO likesDTO);
    Like update(LikeDTO likesDTO, String id) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
