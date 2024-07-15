package com.fsocial.services;

import com.fsocial.dtos.FollowDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Follow;
import com.fsocial.models.User;
import com.fsocial.repositories.UserRepository;
import com.fsocial.responses.FollowResponses;
import com.fsocial.repositories.FollowRepository;
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
    public List<FollowResponses> getAll() {
        List<Follow> follows = followRepository.findAll();
        return follows.stream().map(follow -> modelMapper.map(follow, FollowResponses.class)).toList();
    }

    @Override
    public FollowResponses getById(String id) throws DataNotFoundException {
        Follow follow = followRepository.findByUser_Id(id);
        return modelMapper.map(follow, FollowResponses.class);
    }

    @Override
    public FollowResponses getFollowersByUserId(String userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Cannot found user with id: " + userId));
        return modelMapper.map(followRepository.findByUser_Id(userId), FollowResponses.class);
    }

    @Override
    public List<FollowResponses> getFollowingsByUserId(String followerId) throws DataNotFoundException {
        Follow follows = followRepository.findByUser_Id(followerId);
        return followRepository.findByFollower_Id(followerId).stream().map(follow -> modelMapper.map(follow,FollowResponses.class)).toList();
    }

    @Override
    public FollowResponses create(FollowDTO followDTO) throws DataNotFoundException {
        User user = userRepository.findById(followDTO.getUser()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + followDTO.getUser()));
        User follower = userRepository.findById(followDTO.getFollower()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + followDTO.getFollower()));
        Follow follow = Follow.builder()
                .user(user)
                .follower(follower)
                .build();
        return modelMapper.map(followRepository.save(follow), FollowResponses.class);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Follow follow = followRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find follow with id: " + id));
        followRepository.delete(follow);
    }
}
