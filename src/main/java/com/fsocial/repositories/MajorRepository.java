package com.fsocial.repositories;

import com.fsocial.models.Major;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MajorRepository extends MongoRepository<Major, String> {
}
