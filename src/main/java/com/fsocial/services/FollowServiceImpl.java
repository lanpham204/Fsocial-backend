package com.fsocial.services;

import com.fsocial.dtos.FollowDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Follow;
import com.fsocial.models.User;
import com.fsocial.respositories.UserRepository;
import com.fsocial.respositories.FollowRepository;
import com.fsocial.services.interfaces.FollowService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Follow> getAll() {
        return followRepository.findAll();
    }

    @Override
    public Follow getById(String id) throws DataNotFoundException {
        return followRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find follow with id: " + id));
    }

    @Override
    public Follow getFollowersByUserId(String userId) throws DataNotFoundException {
        return followRepository.findByUserId(userId);
    }

    @Override
    public List<Follow> getFollowingsByUserId(String followerId) throws DataNotFoundException {
        return followRepository.findByFollowerId(followerId);
    }

    @Override
    public Follow create(FollowDTO followDTO) throws DataNotFoundException {
        Follow follow = modelMapper.map(followDTO, Follow.class);
        return followRepository.save(follow);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Follow follow = followRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find follow with id: " + id));
        followRepository.delete(follow);
    }
}
