package com.fsocial.services;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Notification;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.responses.NotificationResponse;
import com.fsocial.respositories.NotificationRepository;
import com.fsocial.respositories.PostRepository;
import com.fsocial.respositories.UserRepository;
import com.fsocial.services.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    @Override
    public List<NotificationResponse> getAll() {
        List<Notification> notifications = notificationsRepository.findAll();
        return notifications.stream().map(notification -> modelMapper.map(notification,NotificationResponse.class)).toList();
    }

    @Override
    public NotificationResponse getById(String id) throws DataNotFoundException {
        return modelMapper.map(notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id)),NotificationResponse.class);
    }

    @Override
    public NotificationResponse create(NotificationDTO notificationsDTO) throws DataNotFoundException{
        List<User>  userReceiver = userRepository.findAllById(notificationsDTO.getUserReceiver().stream().map(userIdDTO -> userIdDTO.getId()).toList());
        User userSender = userRepository.findById(notificationsDTO.getUserSendId()).orElseThrow(() -> new DataNotFoundException("Can not found User with: " + notificationsDTO.getUserSendId()));
        Post post = postRepository.findById(notificationsDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("can not found Post with: "+notificationsDTO.getPostId()));
        Notification notification = Notification.builder()
                .post(post)
                .userSend(userSender)
                .userReceiver(userReceiver)
                .type(notificationsDTO.getType())
                .description(notificationsDTO.getDescription())
                .link(notificationsDTO.getLink())
                .createAt(LocalDateTime.now())
                .build();
        return modelMapper.map(notificationsRepository.save(notification),NotificationResponse.class);
    }

    @Override
    public NotificationResponse update(NotificationDTO notificationsDTO, String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
        List<User>  userReceiver = userRepository.findAllById(notificationsDTO.getUserReceiver().stream().map(userIdDTO -> userIdDTO.getId()).toList());
        User userSender = userRepository.findById(notificationsDTO.getUserSendId()).orElseThrow(() -> new DataNotFoundException("Can not found User with: " + notificationsDTO.getUserSendId()));
        Post post = postRepository.findById(notificationsDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("can not found Post with: "+notificationsDTO.getPostId()));
        notification = Notification.builder()
                .post(post)
                .userSend(userSender)
                .userReceiver(userReceiver)
                .type(notificationsDTO.getType())
                .link(notificationsDTO.getLink())
                .description(notificationsDTO.getDescription())
                .createAt(LocalDateTime.now())
                .build();
        return modelMapper.map(notificationsRepository.save(notification),NotificationResponse.class);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
        notificationsRepository.delete(notification);
    }
}
