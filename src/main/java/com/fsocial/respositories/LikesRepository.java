package com.fsocial.respositories;

import com.fsocial.models.Likes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikesRepository extends MongoRepository<Likes, String> {
}
