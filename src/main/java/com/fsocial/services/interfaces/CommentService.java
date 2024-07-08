package com.fsocial.services.interfaces;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;
import com.fsocial.responses.CommentResponses;

import java.util.List;

public interface CommentService {
    List<CommentResponses> getAll();
    CommentResponses getById(String id) throws DataNotFoundException;
    List<CommentResponses> getByPostId(String postId) throws DataNotFoundException;
    List<CommentResponses> getByUserId(String userId) throws DataNotFoundException;
    CommentResponses create(CommentDTO commentDTO)throws DataNotFoundException;
    CommentResponses update(String id, CommentDTO commentDTO) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
