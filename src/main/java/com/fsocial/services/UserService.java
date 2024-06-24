package com.fsocial.services;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.exceptions.ExistingException;
import com.fsocial.models.User;
import com.fsocial.responses.UserResponse;
import com.fsocial.respositories.RoleRepository;
import com.fsocial.respositories.UserRepository;
import com.fsocial.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    @Override
    public UserResponse create(UserDto userDto) throws DataNotFoundException {
        if(userRepository.existsById(userDto.getId())){
            throw new ExistingException("User with id " + userDto.getId() + " already exists");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setRole(roleRepository.findById("USER").orElseThrow(()->new DataNotFoundException("Could not find role with id: USER")));
        return modelMapper.map(userRepository.save(user),UserResponse.class);
    }

    @Override
    public UserResponse update(String id, UserUpdateInfoDto userDto) throws DataNotFoundException {
        User user =userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Could not find user with id: " + id));
        modelMapper.map(userDto, user);
        if(userDto.getRole()!=null){
           user.setRole(roleRepository.findById(userDto.getRole()).orElseThrow(()->new DataNotFoundException("Could not find role with id: "+user.getRole())));
        }else{
            user.setRole(roleRepository.findById("USER").orElseThrow(()->new DataNotFoundException("Could not find role with id: USER")));
        }
        return modelMapper.map(userRepository.save(user),UserResponse.class);
    }
    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(String id) throws DataNotFoundException {
        return modelMapper.map(userRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cannot find with id: "+id)),UserResponse.class);
        }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }
}
