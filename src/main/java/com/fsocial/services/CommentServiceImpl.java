package com.fsocial.services;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.repositories.PostRepository;
import com.fsocial.repositories.UserRepository;
import com.fsocial.responses.CommentResponses;
import com.fsocial.repositories.CommentRepository;
import com.fsocial.services.interfaces.CommentService;
import com.fsocial.utils.CloudinaryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));

        // Nếu cần cập nhật các thuộc tính khác của comment
        if (commentDTO.getComment() != null) {
            existingComment.setComment(commentDTO.getComment());
        }

        if (commentDTO.getImage() != null) {
            existingComment.setImage(commentDTO.getImage());
        }

        existingComment.setCommentAt(LocalDateTime.now()); // Cập nhật thời gian comment

        // Lưu lại bình luận đã cập nhật vào cơ sở dữ liệu
        Comment updatedComment = commentRepository.save(existingComment);

        // Trả về đối tượng CommentResponses đã được cập nhật
        return modelMapper.map(updatedComment, CommentResponses.class);
    }


    @Override
    public void delete(String id) throws DataNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id: " + id));
        commentRepository.delete(comment);
    }
}
