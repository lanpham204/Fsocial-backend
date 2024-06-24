package com.fsocial.services.interfaces;

import com.fsocial.dtos.FollowDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> getAll();
    Follow getById(String id) throws DataNotFoundException;
    Follow getFollowersByUserId(String userId) throws DataNotFoundException;
    List<Follow> getFollowingsByUserId(String followerId) throws DataNotFoundException;
    Follow create(FollowDTO followDTO) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
