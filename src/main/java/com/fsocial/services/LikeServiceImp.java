package com.fsocial.services;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Like;
import com.fsocial.respositories.LikeRepository;
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
    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like getById(String id) throws DataNotFoundException {
        return likeRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot found like with id: "+id));
    }

    @Override
    public Like getByPost(String postId) throws DataNotFoundException {
        return likeRepository.findLikeByPostId(postId);
    }

    @Override
    public Like getByUser(String userId) throws DataNotFoundException {
        return likeRepository.findLikeByUser_Id(userId);
    }

    @Override
    public Like create(LikeDTO likesDTO) {
        return likeRepository.save(modelMapper.map(likesDTO,Like.class));
    }

    @Override
    public Like update(LikeDTO likesDTO, String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        modelMapper.map(likesDTO,like);
        return likeRepository.save(like);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Like like = likeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found like with id: "+id));
        likeRepository.delete(like);
    }
}
