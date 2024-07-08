package com.fsocial.services;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Notification;
import com.fsocial.repositories.NotificationRepository;
import com.fsocial.services.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationsRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Notification> getAll() {
        return notificationsRepository.findAll();
    }

    @Override
    public Notification getById(String id) throws DataNotFoundException {
        return notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
    }

    @Override
    public Notification create(NotificationDTO notificationsDTO){
        return notificationsRepository.save(modelMapper.map(notificationsDTO,Notification.class));
    }

    @Override
    public Notification update(NotificationDTO notificationsDTO, String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
        modelMapper.map(notificationsDTO,Notification.class);
        return notificationsRepository.save(notification);
    }

    @Override
    public void delete(String id) throws DataNotFoundException {
        Notification notification = notificationsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot found notification with id: "+id));
        notificationsRepository.delete(notification);
    }
}
