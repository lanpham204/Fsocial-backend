package com.fsocial.controllers;

import com.fsocial.dtos.FollowDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Follow;
import com.fsocial.services.interfaces.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @GetMapping
    public ResponseEntity<?> getAllFollow() {
        try {
            return ResponseEntity.ok(followService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFollowById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(followService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFollowersByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(followService.getFollowersByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/follower/{followerId}")
    public ResponseEntity<?> getFollowingsByUserId(@PathVariable String followerId) {
        try {
            return ResponseEntity.ok(followService.getFollowingsByUserId(followerId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createFollow(@RequestBody FollowDTO followDTO) {
        try {
            return ResponseEntity.ok(followService.create(followDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFollow(@PathVariable String id) {
        try {
            followService.delete(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
