package com.fsocial.repositories;

import com.fsocial.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
    Page<Post> findByMajorId(String majorId, Pageable pageable);
    Page<Post> findByActiveIsFalse(Pageable pageable);
    Page<Post> findByActiveIsTrue(Pageable pageable);
}
