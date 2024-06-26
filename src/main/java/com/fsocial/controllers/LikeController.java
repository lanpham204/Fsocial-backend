package com.fsocial.controllers;

import com.fsocial.dtos.LikeDTO;
import com.fsocial.models.Like;
import com.fsocial.services.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<?> getAllLike(){
        try {
            return ResponseEntity.ok(likeService.getAll());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLikeById(@PathVariable String id){
        try {
            return ResponseEntity.ok(likeService.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/post/post-id")
    public ResponseEntity<?> getlikeByPostId(@RequestParam("postId") String postId){
        try {
            return ResponseEntity.ok(likeService.getByPost(postId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/user-id")
    public ResponseEntity<?> getlikeByUserId(@RequestParam("userid") String postId){
        try {
            return ResponseEntity.ok(likeService.getByUser(postId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createLike(@RequestBody LikeDTO likeDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(likeService.create(likeDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLike(@RequestBody LikeDTO likeDTO, @PathVariable String id){
        try {
            return ResponseEntity.ok(likeService.update(likeDTO,id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable String id){
        try {
            likeService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
