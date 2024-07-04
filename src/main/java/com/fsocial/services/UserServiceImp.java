package com.fsocial.services;

import com.fsocial.dtos.UserDto;
import com.fsocial.dtos.UserUpdateInfoDto;
import com.fsocial.exceptions.CodeInvalidException;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.exceptions.ExistingException;
import com.fsocial.models.Report;
import com.fsocial.models.User;
import com.fsocial.responses.UserResponse;
import com.fsocial.repositories.RoleRepository;
import com.fsocial.repositories.UserRepository;
import com.fsocial.services.interfaces.MailerService;
import com.fsocial.services.interfaces.ReportService;
import com.fsocial.services.interfaces.UserService;
import com.fsocial.utils.CloudinaryUtil;

import com.fsocial.utils.JwtTokenUtil;
import com.fsocial.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final RoleRepository roleRepository;
  private final CloudinaryUtil cloudinaryUtil;
  private final ReportService reportService;
  private final MailerService mailerService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  @Override
  public UserResponse uploadImage(String id, MultipartFile file) throws DataNotFoundException, IOException {
    User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
    user.setAvatar(cloudinaryUtil.saveToCloudinary(file));
    return modelMapper.map(userRepository.save(user), UserResponse.class);
  }

  @Override
  public UserResponse uploadBackgroundImage(String id, MultipartFile file) throws DataNotFoundException, IOException {
    User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
    user.setBackground(cloudinaryUtil.saveToCloudinary(file));
    return modelMapper.map(userRepository.save(user), UserResponse.class);
  }

  @Override
  public UserResponse create(UserDto userDto) throws DataNotFoundException {
    if (userRepository.existsById(userDto.getId()) ) {
      throw new ExistingException("User with id " + userDto.getId() + " already exists");
    }
    if( userRepository.existsByEmail(userDto.getEmail())) {
      throw new ExistingException("User with email " + userDto.getEmail() + " already exists");
    }
    User user = modelMapper.map(userDto, User.class);
    String code=RandomUtil.randomString();
    user.setId(user.getId().toUpperCase());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(roleRepository.findById("USER")
        .orElseThrow(() -> new DataNotFoundException("Could not find role with id: USER")));
    reportService.create(Report.builder().user(user).code(code).build());
    mailerService.queue(user.getEmail(), "Ma xac nhan email",code);
    return modelMapper.map(userRepository.save(user), UserResponse.class);
  }

  @Override
  public String login(String email, String password) throws DataNotFoundException {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new DataNotFoundException("Invalid email or password"));
    if(!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Invalid email or password");
    }
    if(!user.isActive()) {
      throw new BadCredentialsException("User is not active");
    }
    if(!user.isNonLocked()) {
      throw new BadCredentialsException("User is being locked");
    }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
    authenticationManager.authenticate(authenticationToken);
    return jwtTokenUtil.generateToken(user);
  }

  @Override
  public UserResponse update(String id, UserUpdateInfoDto userDto) throws DataNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException("Could not find user with id: " + id));
    modelMapper.map(userDto, user);
    if (userDto.getRole() != null) {
      user.setRole(roleRepository.findById(userDto.getRole())
          .orElseThrow(() -> new DataNotFoundException("Could not find role with id: " + user.getRole())));
    } else {
      user.setRole(roleRepository.findById("USER")
          .orElseThrow(() -> new DataNotFoundException("Could not find role with id: USER")));
    }
    return modelMapper.map(userRepository.save(user), UserResponse.class);
  }


  @Override
  public void delete(String id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserResponse findById(String id) throws DataNotFoundException {
    return modelMapper.map(
        userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find with id: " + id)),
        UserResponse.class);
  }

  @Override
  public List<UserResponse> findAll() {
    return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse validatorEmail(String reportUserId, String code) throws DataNotFoundException {
    reportUserId = reportUserId.toUpperCase();
   User user=userRepository.findById(reportUserId).orElseThrow(()-> new DataNotFoundException("User not found"));
   Report report=reportService.findByUserId(user.getId());
   if(report.getCode().equals(code)){
    reportService.delete(report.getId());
    user.setActive(true);
    return modelMapper.map( userRepository.save(user), UserResponse.class);
   }
   throw new CodeInvalidException("Invalid code");
  }
}
