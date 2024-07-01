package com.fsocial.services;

import com.fsocial.dtos.PostDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.*;
import com.fsocial.responses.PostListResponse;
import com.fsocial.responses.PostResponse;
import com.fsocial.repositories.*;
import com.fsocial.utils.CloudinaryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements com.fsocial.services.interfaces.PostService {
    private final ModelMapper modelMapper;
    private final MajorRepository majorRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CloudinaryUtil cloudinaryUtil;
    @Override
    public PostResponse create(PostDTO postDTO) throws DataNotFoundException, IOException {
        User user = userRepository.findById(postDTO.getUserId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + postDTO.getUserId()));
        Major major = majorRepository.findById(postDTO.getMajorId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found major with id: " + postDTO.getMajorId()));
        List<Image> images = new ArrayList<>();
            for (MultipartFile imageMulti : postDTO.getImagesMulti()) {
                if (imageMulti != null) {
                Image image = new Image();
                image.setImage(cloudinaryUtil.saveToCloudinary(imageMulti));
                image.setId(UUID.randomUUID().toString());
                images.add(image);
                }

            }
        List<File> files = new ArrayList<>();
            for (MultipartFile fileMulti : postDTO.getFilesMulti()) {
                if (fileMulti != null) {
                File file = new File();
                file.setFile(cloudinaryUtil.saveToCloudinary(fileMulti));
                file.setId(UUID.randomUUID().toString());
                files.add(file);
                }
            }
        Post post = Post.builder()
                .user(user)
                .content(postDTO.getContent())
                .isActive(postDTO.isActive())
                .files(files)
                .images(images)
                .major(major)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public PostListResponse getPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);
        return PostListResponse.builder()
                .posts(postPage.getContent().stream().map(post -> modelMapper.map(post, PostResponse.class)).toList())
                .totalPage(postPage.getTotalPages()).build();
    }

    @Override
    public PostResponse update(PostDTO postDTO, String id) throws DataNotFoundException {
        User user = userRepository.findById(postDTO.getUserId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + postDTO.getUserId()));
        Major major = majorRepository.findById(postDTO.getMajorId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found major with id: " + postDTO.getMajorId()));
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + id));
        post = Post.builder()
                .user(user)
                .content(postDTO.getContent())
                .isActive(postDTO.isActive())
                .files(post.getFiles())
                .images(post.getImages())
                .major(major)
                .updatedAt(post.getUpdatedAt())
                .createdAt(LocalDateTime.now())
                .id(post.getId())
                .build();
        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public PostResponse getById(String id)  {
        return modelMapper.map(postRepository.findById(id), PostResponse.class);
    }

    @Override
    public List<PostResponse> getByUserId(String userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + userId));
        return postRepository.findByUserId(userId).stream().map(post -> modelMapper.map(post, PostResponse.class)).toList();
    }

    @Override
    public PostListResponse getByMajorId(String majorId,Pageable pageable) throws DataNotFoundException {
        Major major = majorRepository.findById(majorId).orElseThrow(
                () -> new DataNotFoundException("Cannot found major with id: " +majorId));
        Page<Post> postPage = postRepository.findByMajorId(majorId,pageable);
        return PostListResponse.builder()
                .posts(postPage.getContent().stream().map(post -> modelMapper.map(post, PostResponse.class)).toList())
                .totalPage(postPage.getTotalPages()).build();
    }
    @Override
    public void delete(String id) throws DataNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + id));
        postRepository.delete(post);
    }
}
