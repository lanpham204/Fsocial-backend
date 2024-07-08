package com.fsocial.controllers;

import com.fsocial.dtos.CommentDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Comment;
import com.fsocial.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        try {
            return ResponseEntity.ok(commentService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(commentService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable String postId) {
        try {
            return ResponseEntity.ok(commentService.getByPostId(postId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCommentsByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(commentService.getByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        try {
            return ResponseEntity.ok(commentService.create(commentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        try {
            return ResponseEntity.ok(commentService.update(id, commentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
