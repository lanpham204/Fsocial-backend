package com.fsocial.respositories;

import com.fsocial.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByPost_Id(String postId);
    List<Comment> findByUser_Id(String userId);
}
