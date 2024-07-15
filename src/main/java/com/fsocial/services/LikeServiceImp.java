package com.fsocial.services;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.repositories.LikeRepository;
import com.fsocial.repositories.PostRepository;
import com.fsocial.repositories.UserRepository;
import com.fsocial.responses.LikeResponse;
import com.fsocial.services.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public List<LikeResponse> getAll() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(like ->  modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public LikeResponse getById(String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found Like by id: "+id));
        return modelMapper.map(like,LikeResponse.class);
    }

    @Override
    public List<LikeResponse> getByPost(String postId) throws DataNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Cannot found Like by post id: "+postId));
        List<Like> likes = likeRepository.findLikeByPost_Id(postId);
        return likes.stream().map(like -> modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public List<LikeResponse> getByUser(String userId) throws DataNotFoundException{
        User user= userRepository.findById(userId).orElseThrow(() ->new DataNotFoundException("Cannot found Like by user id: "+userId));
        List<Like> likes = likeRepository.findLikeByUser_Id(userId);
        return likes.stream().map(like -> modelMapper.map(like,LikeResponse.class)).toList();
    }

    @Override
    public LikeResponse create(LikeDTO likesDTO) throws DataNotFoundException{
        User user = userRepository.findById(likesDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Cannot found Like by user id: "+ likesDTO.getUserId()));
        Post post = postRepository.findById(likesDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Cannot found Like by post id: "+likesDTO.getPostId()));
        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();
        return modelMapper.map(likeRepository.save(like),LikeResponse.class);
    }

    @Override
    public LikeResponse update(LikeDTO likesDTO, String id) throws DataNotFoundException {
        User user = userRepository.findById(likesDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Cannot found Like by user id: "+ likesDTO.getUserId()));
        Post post = postRepository.findById(likesDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Cannot found Like by post id: "+likesDTO.getPostId()));
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        like.setUser(user);
        like.setPost(post);
        return modelMapper.map(likeRepository.save(like),LikeResponse.class);

    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        likeRepository.delete(like);
    }
}
