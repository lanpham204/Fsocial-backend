package com.fsocial.services.interfaces;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.User;
import com.fsocial.responses.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse create (UserDto userDto) throws DataNotFoundException;
    UserResponse update (String id, UserUpdateInfoDto userDto) throws DataNotFoundException;
    void delete (String id);
    UserResponse findById (String id) throws DataNotFoundException;
    List<UserResponse> findAll();
}
