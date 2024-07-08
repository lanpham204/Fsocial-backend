package com.fsocial.respositories;

import com.fsocial.models.Major;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MajorRepository extends MongoRepository<Major, String> {
}
