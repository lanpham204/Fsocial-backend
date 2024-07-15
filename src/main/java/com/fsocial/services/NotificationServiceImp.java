package com.fsocial.services;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Notification;
import com.fsocial.models.Post;
import com.fsocial.models.User;
import com.fsocial.repositories.NotificationRepository;
import com.fsocial.repositories.PostRepository;
import com.fsocial.repositories.UserRepository;
import com.fsocial.responses.NotificationResponse;
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
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public List<NotificationResponse> getAll() {
        List<Notification> notifications = notificationsRepository.findAll();
        return notifications.stream().map(notification -> modelMapper.map(notification,NotificationResponse.class)).toList();
    }

    @Override
    public NotificationResponse getById(String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));

        return modelMapper.map(notification,NotificationResponse.class);
    }

    @Override
    public NotificationResponse create(NotificationDTO notificationsDTO) throws DataNotFoundException {
        User userSend = userRepository.findById(notificationsDTO.getUserSendId()).orElseThrow(()-> new DataNotFoundException("Cannot found Notification by user send id: "+notificationsDTO.getUserSendId()));
        Post post = postRepository.findById(notificationsDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Cannot found Post by id: "+ notificationsDTO.getPostId()));
        List<User> userReceiver = userRepository.findAllById(notificationsDTO.getUserReceiver());
        Notification notification = Notification.builder()
                .userSend(userSend)
                .userReceiver(userReceiver)
                .link(notificationsDTO.getLink())
                .type(notificationsDTO.getType())
                .post(post)
                .createAt(LocalDateTime.now())
                .build();
        return modelMapper.map(notificationsRepository.save(notification),NotificationResponse.class);
    }

    @Override
    public NotificationResponse update(NotificationDTO notificationsDTO, String id) throws DataNotFoundException {
        User userSend = userRepository.findById(notificationsDTO.getUserSendId()).orElseThrow(()-> new DataNotFoundException("Cannot found Notification by user send id: "+notificationsDTO.getUserSendId()));
        Post post = postRepository.findById(notificationsDTO.getPostId()).orElseThrow(() -> new DataNotFoundException("Cannot found Post by id: "+ notificationsDTO.getPostId()));
        List<User> userReceiver = userRepository.findAllById(notificationsDTO.getUserReceiver());
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found Notification by id: "+id));
        notification.setPost(post);
        notification.setUserSend(userSend);
        notification.setUserReceiver(userReceiver);
        notification.setLink(notificationsDTO.getLink());
        notification.setDescription(notificationsDTO.getDescription());
        notification.setType(notificationsDTO.getType());
        notification.setCreateAt(LocalDateTime.now());
        return modelMapper.map(notificationsRepository.save(notification),NotificationResponse.class);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
        notificationsRepository.delete(notification);
    }
}
