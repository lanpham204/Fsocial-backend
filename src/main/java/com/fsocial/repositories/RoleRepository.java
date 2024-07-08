package com.fsocial.repositories;

import com.fsocial.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>{}