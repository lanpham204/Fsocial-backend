package com.fsocial.respositories;

import com.fsocial.models.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowRepository extends MongoRepository<Follow, String> {
    //find user who following
    Follow findByUser_Id(String userId);
    //find user who follower
    List<Follow> findByFollower_Id(String followerId);
}
