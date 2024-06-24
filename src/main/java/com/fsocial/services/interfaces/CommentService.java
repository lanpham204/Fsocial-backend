package com.fsocial.services.interfaces;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getById(String id) throws DataNotFoundException;
    List<Comment> getByPostId(String postId) throws DataNotFoundException;
    List<Comment> getByUserId(String userId) throws DataNotFoundException;
    Comment create(CommentDTO commentDTO);
    Comment update(String id, CommentDTO commentDTO) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
