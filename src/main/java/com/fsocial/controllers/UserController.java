package com.fsocial.controllers;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.dtos.ValidatorEmail;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.UserResponse;
import com.fsocial.services.interfaces.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Validated @RequestBody UserDto userDto) throws DataNotFoundException {
    return ResponseEntity.ok(userService.create(userDto));
  }

  @PutMapping("{id}")
  public ResponseEntity<UserResponse> updateUser(@PathVariable String id,
      @Validated @RequestBody UserUpdateInfoDto userDto) throws DataNotFoundException {
    return ResponseEntity.ok(userService.update(id, userDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable String id) throws DataNotFoundException {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping("avatar/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable String id, @ModelAttribute MultipartFile file)
      throws DataNotFoundException, IOException {

    return ResponseEntity.ok(userService.uploadImage(id, file));
  }

  @PutMapping(path = "background/{id}")
  public ResponseEntity<UserResponse> getUse(@PathVariable String id, @ModelAttribute MultipartFile file)
      throws DataNotFoundException, IOException {
    return ResponseEntity.ok(userService.uploadBackgroundImage(id, file));
  }
  @PostMapping("validator-email/{id}")
  public ResponseEntity<UserResponse> validateEmail(@PathVariable String id, @RequestBody ValidatorEmail code) throws DataNotFoundException {
    return ResponseEntity.<UserResponse>ok(userService.validatorEmail(id, code.getCode()));
  }

}
