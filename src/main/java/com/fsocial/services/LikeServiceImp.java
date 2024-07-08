package com.fsocial.services;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.responses.LikeResponse;
import com.fsocial.responses.PostResponse;
import com.fsocial.respositories.LikeRepository;
import com.fsocial.respositories.PostRepository;
import com.fsocial.respositories.UserRepository;
import com.fsocial.services.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<LikeResponse> getAll() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(like -> modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public LikeResponse getById(String id) throws DataNotFoundException {
        return modelMapper.map(likeRepository.findLikeByPost_Id(id),LikeResponse.class);
    }

    @Override
    public List<LikeResponse> getByPost(String postId) {
        List<Like> likes = likeRepository.findLikeByPost_Id(postId);
        return likes.stream().map(like -> modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public List<LikeResponse> getByUser(String userId) {
        List<Like> likes = likeRepository.findLikeByUser_Id(userId);
        return likes.stream().map(like -> modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public LikeResponse create(LikeDTO likesDTO) throws DataNotFoundException{
        User user = userRepository.findById(likesDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Can not faund user wit id: "+likesDTO.getUserId()));
        Post post = postRepository.findById(likesDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Can not faund user wit id: "+likesDTO.getUserId()));
        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        return modelMapper.map(likeRepository.save(like), LikeResponse.class);
    }

    @Override
    public LikeResponse update(LikeDTO likesDTO, String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        User user = userRepository.findById(likesDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Cannot found like with User: "+likesDTO.getUserId()));
        Post post = postRepository.findById(likesDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Cannot found like with Post: "+likesDTO.getPostId()));
        like = Like.builder()
                .user(user)
                .post(post)
                .id(like.getId())
                .build();
        return modelMapper.map(likeRepository.save(like), LikeResponse.class);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        likeRepository.delete(like);
    }

}
