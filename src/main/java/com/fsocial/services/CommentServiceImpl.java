package com.fsocial.services;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;
import com.fsocial.models.Image;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.responses.CommentResponses;
import com.fsocial.respositories.CommentRepository;
import com.fsocial.respositories.PostRepository;
import com.fsocial.respositories.UserRepository;
import com.fsocial.services.interfaces.CommentService;
import com.fsocial.utils.CloudinaryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CloudinaryUtil cloudinaryUtil;

    @Override
    public List<CommentResponses> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> modelMapper.map(comment,CommentResponses.class)).toList();
    }

    @Override
    public CommentResponses getById(String id) throws DataNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
        return modelMapper.map(comment, CommentResponses.class);
    }

    @Override
    public List<CommentResponses> getByPostId(String postId) throws DataNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Cannot found user with id: " + postId));
        return commentRepository.findByPost_Id(postId).stream().map(comment -> modelMapper.map(comment,CommentResponses.class)).toList();
    }

    @Override
    public List<CommentResponses> getByUserId(String userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Cannot found user with id: " + userId));
        return commentRepository.findByUser_Id(userId).stream().map(comment -> modelMapper.map(comment,CommentResponses.class)).toList();
    }

    @Override
    public CommentResponses create(CommentDTO commentDTO) throws DataNotFoundException {
        User user = userRepository.findById(commentDTO.getUser()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + commentDTO.getUser()));
        Post post = postRepository.findById(commentDTO.getPost()).orElseThrow(
                () -> new DataNotFoundException("Cannot found post with id: " + commentDTO.getPost()));
        Comment comment = Comment.builder()
                .user(user)
                .commentAt(LocalDateTime.now())
                .comment(commentDTO.getComment())
                .image(commentDTO.getImage())
                .post(post)
                .build();
        return modelMapper.map(commentRepository.save(comment), CommentResponses.class);
    }

    @Override
    public CommentResponses update(String id, CommentDTO commentDTO) throws DataNotFoundException {
    return null;
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
        commentRepository.delete(comment);
    }
}
