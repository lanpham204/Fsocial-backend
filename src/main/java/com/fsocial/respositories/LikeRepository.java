package com.fsocial.respositories;

import com.fsocial.models.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    Like findLikeByPostId( String post_id);
    Like findLikeByUser_Id(String userId);
}
