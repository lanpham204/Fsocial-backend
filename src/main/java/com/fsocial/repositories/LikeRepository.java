package com.fsocial.repositories;

import com.fsocial.models.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findLikeByPost_Id(String post_id);
    List<Like> findLikeByUser_Id(String userId);
}
