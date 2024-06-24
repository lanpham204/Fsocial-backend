package com.fsocial.controllers;


import com.fsocial.dtos.PostDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.PostListResponse;
import com.fsocial.responses.PostResponse;
import com.fsocial.services.interfaces.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/posts")
@RequiredArgsConstructor
public class PostController {
    private final IPostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponse> createPost(@Validated @ModelAttribute("postDTO") PostDTO postDTO,
                                                   @ModelAttribute("files") List<MultipartFile> files,
                                                   @ModelAttribute("images") List<MultipartFile> images) throws DataNotFoundException, IOException {
        if (files != null) {
            postDTO.setFilesMulti(files);
        } else {
            postDTO.setFilesMulti(new ArrayList<>());
        }
        if (images != null) {
            postDTO.setImagesMulti(images);
        } else {
            postDTO.setImagesMulti(new ArrayList<>());
        }
        PostResponse postResponse = postService.create(postDTO);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        PostListResponse posts = postService.getPosts(pageRequest);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id) throws DataNotFoundException {
        PostResponse postResponse = postService.getById(id);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@Validated @ModelAttribute("postDTO") PostDTO postDTO,
                                                   @ModelAttribute(value = "files") List<MultipartFile> files,
                                                   @ModelAttribute(value = "images") List<MultipartFile> images,
                                                   @PathVariable String id) throws DataNotFoundException, IOException {
        postDTO.setFilesMulti(files);
        postDTO.setImagesMulti(images);
        PostResponse postResponse = postService.update(postDTO, id);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable String userId) throws DataNotFoundException {
        List<PostResponse> posts = postService.getByUserId(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/major/{majorId}")
    public ResponseEntity<?> getPostsByMajorId(@PathVariable String majorId,
                                               @RequestParam(required = false,defaultValue = "0") int page,
                                               @RequestParam(required = false,defaultValue = "10") int size) throws DataNotFoundException {
        PageRequest pageRequest = PageRequest.of(page, size);
        PostListResponse posts = postService.getByMajorId(majorId, pageRequest);
        return ResponseEntity.ok(posts);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) throws DataNotFoundException {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
