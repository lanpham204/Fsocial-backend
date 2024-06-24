package com.fsocial.services;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;
import com.fsocial.respositories.CommentRepository;
import com.fsocial.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(String id) throws DataNotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
    }

    @Override
    public List<Comment> getByPostId(String postId) throws DataNotFoundException {
        return commentRepository.findByPost_Id(postId);
    }

    @Override
    public List<Comment> getByUserId(String userId) throws DataNotFoundException {
        return commentRepository.findByUser_Id(userId);
    }

    @Override
    public Comment create(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(String id, CommentDTO commentDTO) throws DataNotFoundException {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
        modelMapper.map(commentDTO, existingComment);
        return commentRepository.save(existingComment);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
        commentRepository.delete(comment);
    }
}
