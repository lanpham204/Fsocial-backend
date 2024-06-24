package com.fsocial.controllers;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.UserResponse;
import com.fsocial.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Validated  @RequestBody UserDto userDto) throws DataNotFoundException {
        return ResponseEntity.ok(userService.create(userDto));
    }
    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Validated @RequestBody UserUpdateInfoDto userDto) throws DataNotFoundException {
        return ResponseEntity.ok(userService.update(id, userDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) throws DataNotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }
}
