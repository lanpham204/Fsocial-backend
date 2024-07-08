package com.fsocial.services.interfaces;

import com.fsocial.dtos.FollowDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Follow;
import com.fsocial.responses.FollowResponses;

import java.util.List;

public interface FollowService {
    List<FollowResponses> getAll();
    FollowResponses getById(String id) throws DataNotFoundException;
    FollowResponses getFollowersByUserId(String userId) throws DataNotFoundException;
    List<FollowResponses> getFollowingsByUserId(String followerId) throws DataNotFoundException;
    FollowResponses create(FollowDTO followDTO) throws DataNotFoundException;
    void delete(String id) throws DataNotFoundException;
}
