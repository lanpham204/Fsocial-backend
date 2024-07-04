package com.fsocial.services.interfaces;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.UserResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
  UserResponse uploadImage(String id, MultipartFile file) throws DataNotFoundException, IOException;

  UserResponse uploadBackgroundImage(String id, MultipartFile file) throws DataNotFoundException, IOException;

  UserResponse create(UserDto userDto) throws DataNotFoundException;
  String login(String email, String password) throws DataNotFoundException;

  UserResponse update(String id, UserUpdateInfoDto userDto) throws DataNotFoundException;

  void delete(String id);

  UserResponse findById(String id) throws DataNotFoundException;

  List<UserResponse> findAll();
  UserResponse validatorEmail(String reportUserId, String code) throws DataNotFoundException;
}
